/*
 * Copyright (c) 2025 - Felipe Desiderati
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
 * LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package demo.sat.tracklist_manager.listener;

import demo.sat.tracklist_manager.domain.Track;
import demo.sat.tracklist_manager.service.TrackService;
import dev.springbloom.core.exception.ApplicationException;
import dev.springbloom.jms.AbstractAsyncMessageListener;
import dev.springbloom.web.notification.client.NotificationClient;
import jakarta.jms.Message;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Locale;

@Slf4j
@Component
public class TrackMessageListener extends AbstractAsyncMessageListener<Track> {

    private final NotificationClient notificationClient;

    private final TrackService trackService;

    private final MessageSource messageSource;

    public TrackMessageListener(
        NotificationClient notificationClient,
        TrackService trackService,
        MessageSource messageSource
    ) {
        this.notificationClient = notificationClient;
        this.trackService = trackService;
        this.messageSource = messageSource;
    }

    @Override
    @SneakyThrows
    protected void receive(Track track) {
        // Simulating a long process of converting audio tracks to a long set of formats.
        Thread.sleep(30000);
        track.setProcessed(true);
        trackService.saveTrack(track);
        notificationClient.broadcastToAll(getMessage("track_saved_successfully"));
    }

    @Override
    protected void handleApplicationException(
        Message jmsMessage,
        Track message,
        ApplicationException applicationException
    ) {
        super.handleApplicationException(jmsMessage, message, applicationException);
        notificationClient.broadcastToAll(
            getMessage(applicationException.getMessage(), applicationException.getArgs())
        );
    }

    private String getMessage(String message, Serializable... args) {
        var defaultMessage = "There was an error while processing a Track!";
        return messageSource != null ? messageSource.getMessage(
            message, args, defaultMessage, Locale.getDefault()
        ) : defaultMessage;
    }
}
