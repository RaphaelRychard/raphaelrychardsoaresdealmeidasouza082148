package br.gov.mt.seplag.artistalbumapi.modules.artist.entity;

import br.gov.mt.seplag.artistalbumapi.modules.album.entity.AlbumEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "artist_album")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArtistAlbumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private ArtistEntity artist;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private AlbumEntity album;

    private String contributionType;
}
