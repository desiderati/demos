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

import dev.springbloom.jms.JmsErrorHandler;
import dev.springbloom.web.notification.client.NotificationClient;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Slf4j
@Primary
@Component
public class TrackMessageErrorHandler extends JmsErrorHandler {

    private final NotificationClient notificationClient;

    @Autowired
    public TrackMessageErrorHandler(
        @Value("${jms.default-queue.max-delivery-attempts:10}") Integer maxDeliveryAttempts,
        NotificationClient notificationClient
    ) {
        super(maxDeliveryAttempts);
        this.notificationClient = notificationClient;
    }

    @Override
    public void handleLastError(@NotNull Throwable throwable) {
        super.handleLastError(throwable);
        notificationClient.broadcastToAll("An internal error occurred while processing track!");
    }
}
