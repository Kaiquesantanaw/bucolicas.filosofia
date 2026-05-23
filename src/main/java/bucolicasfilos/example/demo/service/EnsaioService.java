package bucolicasfilos.example.demo.service;

import bucolicasfilos.example.demo.model.Ensaio;
import bucolicasfilos.example.demo.repository.EnsaioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnsaioService {

    private final EnsaioRepository ensaioRepository;

    public EnsaioService(EnsaioRepository ensaioRepository) {
        this.ensaioRepository = ensaioRepository;
    }

    public List<Ensaio> findAll() {
        return ensaioRepository.findAll();
    }

    public Optional<Ensaio> findById(Long id) {
        return ensaioRepository.findById(id);
    }

    public Ensaio save(Ensaio ensaio) {
        return ensaioRepository.save(ensaio);
    }

    public Ensaio update(Long id, Ensaio dados) {
        Ensaio ensaio = ensaioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ensaio não encontrado: " + id));
        ensaio.setTitulo(dados.getTitulo());
        ensaio.setConteudo(dados.getConteudo());
        ensaio.setAutorFilosofo(dados.getAutorFilosofo());
        return ensaioRepository.save(ensaio);
    }

    public void delete(Long id) {
        ensaioRepository.deleteById(id);
    }
}
