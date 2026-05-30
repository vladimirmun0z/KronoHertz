package com.kronohertz;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kronohertz.models.MarcaReloj;
import com.kronohertz.models.Rol;
import com.kronohertz.models.Reloj;
import com.kronohertz.models.Usuario;
import com.kronohertz.repository.IRelojRepository;
import com.kronohertz.repository.IRolRepository;
import com.kronohertz.repository.IUsuarioRepository;
import com.kronohertz.services.IMarcaService;

@SpringBootApplication
public class KronoHertzApplication implements CommandLineRunner {
    
    @Autowired
    private IRelojRepository repoReloj;
    
    @Autowired
    private IMarcaService serviceMarca; 
    
    @Autowired
    private IRolRepository repoRol;
    
    @Autowired
    private IUsuarioRepository repoUsuario;

    public static void main(String[] args) {
        SpringApplication.run(KronoHertzApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Descomenta el método que desees probar en la consola de Eclipse:
        // testConexion();
        // guardar();
        // buscarPorId();
        // modificar();
        // eliminarPorId();
        // cantidadMarcas();
        // buscarTodos();
        // existeId();
        // guardarTodas();
        
        // MÉTODOS DE RELACIONES
        // buscarRelojes();
        // guardarReloj();
        // crearRolesKrono();
        // crearUsuarioConDosRoles();
        // getUsuario();
        // buscarRelojEstatus();
    }
    
    // ==========================================
    // LÓGICA DE RELACIONES Y CONSULTAS AVANZADAS
    // ==========================================
    
    private void buscarRelojes() {
        List<Reloj> lista = repoReloj.findAll();
        for (Reloj reloj : lista) {
            String nombreMarca = (reloj.getMarcaReloj() != null) ? reloj.getMarcaReloj().getNomMarca() : "Sin Marca";
            System.out.println(reloj.getId() + " " + reloj.getNomReloj() + " | Marca: " + nombreMarca);
        }
    }
    
    private void guardarReloj() {
        Reloj reloj = new Reloj();
        reloj.setNomReloj("Edifice EFV-100D-1AVUEF");
        reloj.setDescripcion("Reloj deportivo clásico de la línea Edifice con esfera negra minimalista.");
        reloj.setFecha(new Date());
        reloj.setCosto(89.00);
        reloj.setActivo(true); // Cambiado según tu Reloj.java (boolean)
        reloj.setDestacado(1);
        reloj.setImagen("edifice.png");
        reloj.setDetalles("Movimiento de cuarzo japonés, resistencia al agua de 100 metros, caja de acero.");
        
        List<MarcaReloj> marcas = serviceMarca.buscarTodo();
        if(!marcas.isEmpty()) {
            reloj.setMarcaReloj(marcas.get(0)); 
            repoReloj.save(reloj);
            System.out.println("¡Reloj guardado y enlazado a la casa manufacturera exitosamente!");
        } else {
            System.out.println("No se pudo guardar el reloj: No existen marcas indexadas en la BD.");
        }
    }
    
    private List<Rol> getListaRoles(){
        List<Rol> lista = new LinkedList<Rol>();
        
        Rol rol1 = new Rol();
        rol1.setNomRol("SuperAdministrador");
        rol1.setDescripcion("Acceso total al inventario de alta relojería de KronoHertz");
            
        Rol rol2 = new Rol();
        rol2.setNomRol("Vendedor");
        rol2.setDescripcion("Gestión de catálogo, marcas e ingreso de piezas");
        
        Rol rol3 = new Rol();
        rol3.setNomRol("Cliente");
        rol3.setDescripcion("Consulta de catálogo, piezas destacadas y reservas");
            
        lista.add(rol1);
        lista.add(rol2);
        lista.add(rol3);
        
        return lista;
    }
        
    private void crearRolesKrono() {
        repoRol.saveAll(getListaRoles());
        System.out.println("Roles de seguridad KronoHertz creados.");
    }
    
    private void crearUsuarioConDosRoles() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Vladi"); // CORREGIDO: nomUsuario -> nombre
        usuario.setEmail("vladi@kronohertz.com");
        usuario.setUsername("vladi_admin");
        usuario.setPassword("krono123");
        usuario.setEstatus(1); // CORREGIDO: setActivo(true) -> setEstatus(1) según tu Usuario.java (Integer)
        usuario.setFechaRegistro(new Date());

        Optional<Rol> rol1 = repoRol.findById(1);
        Optional<Rol> rol2 = repoRol.findById(2);

        // Descomenta si cuentas con la colección @ManyToMany getRoles() o getPerfil() en tu modelo Usuario:
        // if(rol1.isPresent()) usuario.getRoles().add(rol1.get());
        // if(rol2.isPresent()) usuario.getRoles().add(rol2.get());

        repoUsuario.save(usuario);
        System.out.println("Usuario administrativo de KronoHertz registrado.");
    }
    
    // CORREGIDO: Se adaptaron los getters a los campos reales de tu entidad Usuario.java
    private void getUsuario() {
        Optional<Usuario> usuario = repoUsuario.findById(1);
        if (usuario.isPresent()) {
            Usuario usu = usuario.get();
            System.out.println("Usuario: " + usu.getNombre()); // CORREGIDO: getNomUsuario() -> getNombre()
            System.out.println("Email: " + usu.getEmail());
            System.out.println("Username: " + usu.getUsername());
        } else {
            System.out.println("Usuario no encontrado");
        }
    }
    
    private void buscarRelojEstatus() {
        List<Reloj> lista = repoReloj.findAll(); 
        for (Reloj r : lista) {
            System.out.println(r.getId() + ": " + r.getNomReloj() + " | Activo: " + r.isActivo());
        }
    }
    
    // ==========================================
    // MÉTODOS CRUD BÁSICOS (MARCAS / MANUFACTURA)
    // ==========================================
    
    private void guardar() {
        MarcaReloj marca = new MarcaReloj();
        marca.setNomMarca("Rolex");
        marca.setDescripcion("Casa manufacturera suiza de lujo icónica por sus modelos herméticos de alta gama.");
        marca.setActivo(true);
        serviceMarca.guardar(marca);
        System.out.println("Casa manufacturera guardada.");
    }
    
    private void buscarPorId() {
        MarcaReloj marca = serviceMarca.buscarPorId(1);
        if (marca != null) {
            System.out.println("Marca encontrada: " + marca.getNomMarca());
        } else {
            System.out.println("Marca no encontrada");
        }
    }
    
    private void modificar() {
        MarcaReloj marca = serviceMarca.buscarPorId(1);
        if (marca != null) {
            marca.setNomMarca("Rolex Internacional");
            marca.setDescripcion("Catálogo actualizado con especificaciones de calibres cronométricos.");
            serviceMarca.guardar(marca);
            System.out.println("Marca modificada con éxito.");
        } else {
            System.out.println("Marca no encontrada para modificar.");
        }
    }
    
    private void eliminarPorId() {
        serviceMarca.eliminar(1);
        System.out.println("Firma manufacturera removida del sistema.");
    }
    
    private void cantidadMarcas() {
        long cantidad = serviceMarca.buscarTodo().size();
        System.out.println("Cantidad de marcas registradas: " + cantidad);
    }
    
    private void eliminarTodo() {
        System.out.println("Operación masiva deshabilitada por seguridad en entorno KronoHertz.");
    }
    
    private void buscarTodos() {
        List<MarcaReloj> marcas = serviceMarca.buscarTodo();
        for (MarcaReloj m : marcas) {
            System.out.println(m.getNomMarca() + " " + m.getDescripcion());
        }
    }
    
    private void existeId() {
        MarcaReloj m = serviceMarca.buscarPorId(4);
        System.out.println("¿La marca con ID 4 existe?: " + (m != null));
    }
    
    private List<MarcaReloj> getMarcasMock() {
        List<MarcaReloj> lista = new LinkedList<MarcaReloj>();
            
        MarcaReloj m1 = new MarcaReloj();
        m1.setNomMarca("Omega");
        m1.setDescripcion("Prestigiosa manufactura conocida por su precisión y misiones espaciales.");
        m1.setActivo(true);
            
        MarcaReloj m2 = new MarcaReloj();
        m2.setNomMarca("Patek Philippe");
        m2.setDescripcion("Referente absoluto de la alta complejidad y relojería tradicional familiar.");
        m2.setActivo(true);
            
        lista.add(m1);
        lista.add(m2);
            
        return lista;
    }
        
    private void guardarTodas() {
        List<MarcaReloj> lista = getMarcasMock();
        for (MarcaReloj m : lista) {
            serviceMarca.guardar(m);
        }
        System.out.println("Lote de marcas guardado de forma secuencial.");
    }
    
    private void testConexion() {
        System.out.println("Probando capa de persistencia en MySQL Workbench...");
        List<MarcaReloj> lista = serviceMarca.buscarTodo();
        if (lista != null) {
            System.out.println("Conexión Exitosa. Repositorios y servicios listos.");
        } else {
            System.out.println("Error crítico: Capa de datos inalcanzable.");
        }
    }
}