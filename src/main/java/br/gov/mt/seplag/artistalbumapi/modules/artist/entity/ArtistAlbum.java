package br.gov.mt.seplag.artistalbumapi.modules.artist.entity;

import br.gov.mt.seplag.artistalbumapi.modules.album.entity.Album;
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
public class ArtistAlbum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;

    private String contributionType;
}
