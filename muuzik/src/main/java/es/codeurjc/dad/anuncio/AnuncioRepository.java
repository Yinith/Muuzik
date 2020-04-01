package es.codeurjc.dad.anuncio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {
	List<Anuncio> findByAnunciante_Id(Long id);
	List<Anuncio> findByAnunciante_Nick(String nick);
	long deleteByAnunciante_Id(Long id);
}