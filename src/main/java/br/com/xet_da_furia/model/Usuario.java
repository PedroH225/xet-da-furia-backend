package br.com.xet_da_furia.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.xet_da_furia.repository.UsuarioRepository;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	private String nome;
	
	private String email;
	
	private String senha;
	
	private LocalDateTime criadoEm;
	
	@OneToMany(mappedBy = "administrador")
	private List<Chat> chatsCriados = new ArrayList<Chat>();
	
	@ManyToMany(mappedBy = "participantes", cascade = CascadeType.PERSIST)
	private List<Chat> chatsParticipados = new ArrayList<Chat>();
	
	@OneToMany(mappedBy = "usuario")
	private List<Mensagem> mensagens = new ArrayList<Mensagem>();

	public Usuario(String nome, String email, String senha) {
		this.id = null;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.criadoEm = LocalDateTime.now();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.emptyList();
	}


	@Override
	public String getPassword() {
		return senha;
	}


	@Override
	public String getUsername() {
		return email;
	}
	
	@Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;

    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }

}
