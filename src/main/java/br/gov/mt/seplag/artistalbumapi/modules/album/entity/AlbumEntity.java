package br.gov.mt.seplag.artistalbumapi.modules.album.entity;

import br.gov.mt.seplag.artistalbumapi.modules.artist.entity.ArtistAlbumEntity;
import br.gov.mt.seplag.artistalbumapi.modules.artist.entity.ArtistEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "albums")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlbumEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 200)
    @Column(nullable = false, length = 200)
    private String title;

    @Column(name = "release_year", nullable = false)
    private int releaseYear;

    @NotBlank
    @Size(max = 200)
    @Column(name = "cover_image_key", nullable = false, length = 200)
    private String coverImageKey;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToMany(mappedBy = "albums")
    private Set<ArtistEntity> artists = new HashSet<>();

    @OneToMany(mappedBy = "album")
    private Set<ArtistAlbumEntity> artistAlbums = new HashSet<>();
}
