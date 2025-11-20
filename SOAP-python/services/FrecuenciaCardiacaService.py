from spyne import rpc, ServiceBase, Integer, Unicode, Iterable
from models.models import Session, Frecuencia
import json


class FrecuenciaCardiacaService(ServiceBase):

    # Crear registro usando parámetros típicos (mapea a nombres de columna comunes)
    @rpc(Integer, Unicode, Integer, _returns=Integer)
    def crear_frecuencia(ctx, bpm, registrado_en, user_id):
        session = Session()
        nueva = Frecuencia()

        # Asignar atributos de forma flexible intentando nombres comunes
        # Frecuencia/bpm
        for attr in ('valor', 'frecuencia', 'bpm', 'valor_bpm'):
            if hasattr(Frecuencia, attr):
                setattr(nueva, attr, bpm)
                break

        # Fecha/timestamp
        for attr in ('registrado_en', 'fecha_registro', 'registro_fecha', 'timestamp', 'fecha'):
            if hasattr(Frecuencia, attr):
                setattr(nueva, attr, registrado_en)
                break

        # Usuario FK
        for attr in ('user_id', 'usuario_id', 'id_usuario'):
            if hasattr(Frecuencia, attr):
                setattr(nueva, attr, user_id)
                break

        session.add(nueva)
        session.commit()

        pk = list(Frecuencia.__table__.primary_key)[0].name
        return getattr(nueva, pk)

    # Crear registro a partir de JSON flexible
    @rpc(Unicode, _returns=Integer)
    def crear_frecuencia_json(ctx, data_json):
        session = Session()
        data = json.loads(data_json)
        nueva = Frecuencia()

        for k, v in data.items():
            if hasattr(Frecuencia, k):
                setattr(nueva, k, v)

        session.add(nueva)
        session.commit()

        pk = list(Frecuencia.__table__.primary_key)[0].name
        return getattr(nueva, pk)

    # Leer registro
    @rpc(Integer, _returns=Unicode)
    def leer_frecuencia(ctx, frecuencia_id):
        session = Session()
        registro = session.get(Frecuencia, frecuencia_id)
        if not registro:
            return "No encontrado"

        # Representación simple: devolver todas las columnas como cadena
        cols = []
        for c in Frecuencia.__table__.columns:
            cols.append(f"{c.name}: {getattr(registro, c.name)}")
        return ", ".join(cols)

    # Listar todas las frecuencias
    @rpc(_returns=Iterable(Unicode))
    def listar_todas_frecuencias(ctx):
        session = Session()
        registros = session.query(Frecuencia).all()
        for r in registros:
            cols = []
            for c in Frecuencia.__table__.columns:
                cols.append(f"{c.name}: {getattr(r, c.name)}")
            yield ", ".join(cols)

    # Actualizar registro
    @rpc(Integer, Integer, Unicode, Integer, _returns=Unicode)
    def actualizar_frecuencia(ctx, frecuencia_id, bpm, registrado_en, user_id):
        session = Session()
        registro = session.get(Frecuencia, frecuencia_id)
        if not registro:
            return "No encontrado"

        for attr in ('valor', 'frecuencia', 'bpm', 'valor_bpm'):
            if hasattr(Frecuencia, attr):
                setattr(registro, attr, bpm)
                break

        for attr in ('registrado_en', 'fecha_registro', 'registro_fecha', 'timestamp', 'fecha'):
            if hasattr(Frecuencia, attr):
                setattr(registro, attr, registrado_en)
                break

        for attr in ('user_id', 'usuario_id', 'id_usuario'):
            if hasattr(Frecuencia, attr):
                setattr(registro, attr, user_id)
                break

        session.commit()
        return "Actualizada"

    # Eliminar registro
    @rpc(Integer, _returns=Unicode)
    def eliminar_frecuencia(ctx, frecuencia_id):
        session = Session()
        registro = session.get(Frecuencia, frecuencia_id)
        if not registro:
            return "No encontrado"
        session.delete(registro)
        session.commit()
        return "Eliminada"
