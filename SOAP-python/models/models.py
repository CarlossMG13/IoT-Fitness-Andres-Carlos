from sqlalchemy import Column, Integer, String, create_engine, Table, ForeignKeyConstraint
from sqlalchemy.orm import declarative_base, sessionmaker, relationship

# Definición del modelo
Base = declarative_base()

# Configuración de la base de datos
engine = create_engine("mysql+pymysql://root:GCYaYvrNIBXbFZGkOWRUzLnmOJKLpkdB@trolley.proxy.rlwy.net:58630/railway")

# Reflect (cargar) la tabla `usuarios` existente en la base de datos.
# Esto evita asumir el nombre de la PK y permite crear una FK consistente.
usuarios_table = Table('usuarios', Base.metadata, autoload_with=engine)
_usuarios_pk_name = list(usuarios_table.primary_key)[0].name


# Mapear clase Usuario sobre la tabla reflejada
class Usuario(Base):
    __table__ = usuarios_table
    dispositivos = relationship('Dispositivo', back_populates='user')
    actividad_fisica = relationship('ActividadFisica', back_populates='user')
    frecuencias = relationship('Frecuencia', back_populates='usuario')

# Reflejar la tabla de frecuencia existente para no asumir nombres de columnas
frecuencia_table = Table('registros_frecuencia_cardiaca', Base.metadata, autoload_with=engine)

class Frecuencia(Base):
    __table__ = frecuencia_table
    usuario = relationship('Usuario', back_populates='frecuencias')

# Definición de la clase Dispositivo con FK dinámica hacia la PK real de `usuarios` 
class Dispositivo(Base):
    __tablename__ = "dispositivos"

    id_dispositivo = Column(Integer, primary_key=True, autoincrement=True)
    tipo_dispositivo = Column(String(100), nullable=False)
    marca = Column(String(100), nullable=False)
    num_serie = Column(String(100), nullable=False, unique=True)
    # Usar el mismo tipo que la PK reflejada de `usuarios` para evitar incompatibilidades
    user_id = Column(usuarios_table.c[_usuarios_pk_name].type, nullable=False)

    __table_args__ = (
        ForeignKeyConstraint(['user_id'], [f"{usuarios_table.name}.{_usuarios_pk_name}"]),
    )

    user = relationship('Usuario', back_populates='dispositivos')

class ActividadFisica(Base):
    __tablename__ = "actividad_fisica"

    id_actividad = Column(Integer, primary_key=True, autoincrement=True)
    tipo_actividad = Column(String(100), nullable=False)
    duracion_minutos = Column(Integer, nullable=False)
    calorias_quemadas = Column(Integer, nullable=False)
    # Usar el mismo tipo que la PK reflejada de `usuarios` para evitar incompatibilidades
    user_id = Column(usuarios_table.c[_usuarios_pk_name].type, nullable=False)

    __table_args__ = (
        ForeignKeyConstraint(['user_id'], [f"{usuarios_table.name}.{_usuarios_pk_name}"]),
    )

    user = relationship('Usuario', back_populates='actividad_fisica')
# Crear solo la tabla `dispositivos` y `actividad_fisica` si no existen; no tocamos las tablas existentes.
Base.metadata.create_all(engine, tables=[Dispositivo.__table__, ActividadFisica.__table__])

Session = sessionmaker(bind=engine)