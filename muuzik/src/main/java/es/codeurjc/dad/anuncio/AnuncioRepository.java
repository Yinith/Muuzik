package es.codeurjc.dad.anuncio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

@CacheConfig(cacheNames="anuncios")
public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {
	
	@CacheEvict(allEntries=true)//Garantiza que no haya ningún dato incorrecto
	Anuncio save(Anuncio anuncio);
	
	//Estos métodos ya estaban implementados pero los incluimos en nuestra interfaz para poder cachearlos.
	@Cacheable
	List<Anuncio> findAll();
		
	@Cacheable
	Page<Anuncio> findAll(Pageable page);
		
	@Cacheable
	List<Anuncio> findByAnunciante_Id(Long id);
	
	@Cacheable
	List<Anuncio> findByAnunciante_Nick(String nick);
	
	Optional<Anuncio> findById(Long id);
	
	@CacheEvict(allEntries=true)
    void deleteById(Long id);
	
	@CacheEvict(allEntries=true)
    void delete(Anuncio anuncio);
	
	@CacheEvict(allEntries=true)
	long deleteByAnunciante_Id(Long id);
	
}