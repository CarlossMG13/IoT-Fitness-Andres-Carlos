from spyne import Application
from spyne.protocol.soap import Soap11
from spyne.server.wsgi import WsgiApplication
from services.DispositivoService import DispositivoService
from services.ActividadFisicaService import ActividadFisicaService
from services.FrecuenciaCardiacaService import FrecuenciaCardiacaService

# Configuración de la aplicación SOAP
application = Application(
    [DispositivoService, ActividadFisicaService, FrecuenciaCardiacaService],
    tns="spyne.dispositivos.app",
    in_protocol=Soap11(validator="lxml"),
    out_protocol=Soap11()
)

# Configuración WSGI
wsgi_app = WsgiApplication(application)

# Ejecutar el servidor
if __name__ == "__main__":
    from wsgiref.simple_server import make_server
    print("Servidor SOAP en http://localhost:8000")
    server = make_server("0.0.0.0", 8000, wsgi_app)
    server.serve_forever()
