from spyne import rpc, ServiceBase, Integer, Unicode, Iterable
from models.models import Session, ActividadFisica
class ActividadFisicaService(ServiceBase):
    # Crear actividad física
    @rpc(Unicode, Integer, Integer, Integer, _returns=Integer)
    def crear_actividad(ctx, tipo_actividad, duracion_minutos, calorias_quemadas, user_id):
        session = Session()
        nueva = ActividadFisica(
            tipo_actividad=tipo_actividad,
            duracion_minutos=duracion_minutos,
            calorias_quemadas=calorias_quemadas,
            user_id=user_id
        )
        session.add(nueva)
        session.commit()
        return nueva.id_actividad
    
    @rpc(Integer, _returns=Unicode)
    def leer_actividad(ctx, actividad_id):
        session = Session()
        actividad = session.get(ActividadFisica, actividad_id)
        if not actividad:
            return "No encontrado"
        return f"{actividad.id_actividad}: {actividad.tipo_actividad}, Duración: {actividad.duracion_minutos} min, Calorías: {actividad.calorias_quemadas}, User ID: {actividad.user_id}"
    
    @rpc(_returns=Iterable(Unicode))
    def listar_todas_actividades(ctx):
        session = Session()
        actividades = session.query(ActividadFisica).all()

        for a in actividades:
            yield f"{a.id_actividad}: {a.tipo_actividad}, Duración: {a.duracion_minutos} min, Calorías: {a.calorias_quemadas}, User ID: {a.user_id}"
    
    @rpc(Integer, Unicode, Integer, Integer, Integer, _returns=Unicode)
    def actualizar_actividad(ctx, actividad_id, tipo_actividad, duracion_minutos, calorias_quemadas, user_id):
        session = Session()
        actividad = session.get(ActividadFisica, actividad_id)
        if not actividad:
            return "No encontrado"
        actividad.tipo_actividad = tipo_actividad
        actividad.duracion_minutos = duracion_minutos
        actividad.calorias_quemadas = calorias_quemadas
        actividad.user_id = user_id
        session.commit()

        return "Actualizada"
    
    @rpc(Integer, _returns=Unicode)
    def eliminar_actividad(ctx, actividad_id):
        session = Session()
        actividad = session.get(ActividadFisica, actividad_id)
        if not actividad:
            return "No encontrado"
        session.delete(actividad)
        session.commit()
        return "Eliminada"
    