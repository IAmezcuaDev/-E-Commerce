package tecnm.demo.controllers;

import tecnm.demo.models.Usuario;
import tecnm.demo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired private UsuarioRepository repo;

    @GetMapping
    public List<Usuario> todos() { return repo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> uno(@PathVariable Long id) {
        Usuario u = repo.findById(id);
        return (u != null) ? ResponseEntity.ok(u) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public String crear(@Valid @RequestBody Usuario u) {
        repo.save(u);
        return "Usuario creado";
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizar(@PathVariable Long id, @Valid @RequestBody Usuario u) {
        return (repo.update(id, u) > 0) ? ResponseEntity.ok("Usuario actualizado") : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        return (repo.delete(id) > 0) ? ResponseEntity.ok("Usuario eliminado") : ResponseEntity.notFound().build();
    }
}