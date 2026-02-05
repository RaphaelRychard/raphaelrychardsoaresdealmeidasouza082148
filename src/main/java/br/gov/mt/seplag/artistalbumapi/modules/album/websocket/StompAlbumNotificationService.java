package br.gov.mt.seplag.artistalbumapi.modules.album.websocket;

import br.gov.mt.seplag.artistalbumapi.modules.album.dto.response.AlbumResponseDTO;
import br.gov.mt.seplag.artistalbumapi.modules.album.services.AlbumNotificationService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class StompAlbumNotificationService implements AlbumNotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    public StompAlbumNotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void notifyNewAlbum(AlbumResponseDTO album) {
        messagingTemplate.convertAndSend("/topic/albums", album);
    }
}