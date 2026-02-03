package br.gov.mt.seplag.artistalbumapi.modules.album.entity;

import br.gov.mt.seplag.artistalbumapi.modules.artist.entity.ArtistAlbum;
import br.gov.mt.seplag.artistalbumapi.modules.artist.entity.Artist;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "albums")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 200)
    @Column(nullable = false, length = 200)
    private String title;

    @Column(name = "release_year", nullable = false)
    private Year releaseYear;

    @NotBlank
    @Size(max = 200)
    @Column(name = "cover_image_key", nullable = false, length = 200)
    private String coverImageKey;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToMany(mappedBy = "albums")
    private Set<Artist> artists = new HashSet<>();

    @OneToMany(mappedBy = "album")
    private Set<ArtistAlbum> artistAlbums = new HashSet<>();

    public boolean isReleaseYearValid() {
        if (releaseYear == null) {
            return false;
        }

        Year earliestValidYear = Year.of(1900);
        Year currentYear = Year.now();

        boolean isAfterEarliest = !releaseYear.isBefore(earliestValidYear);
        boolean isBeforeCurrent = !releaseYear.isAfter(currentYear);

        return isAfterEarliest && isBeforeCurrent;
    }
}
