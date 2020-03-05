package es.codeurjc.dad.anuncio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {
	List<Anuncio> findByUser_Id(Long id);
	List<Anuncio> findByUser_Nick(String nick);
	long deleteByUser_Id(Long id);
}