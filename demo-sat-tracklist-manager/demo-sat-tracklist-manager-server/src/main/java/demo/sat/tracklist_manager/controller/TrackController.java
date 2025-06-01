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
package demo.sat.tracklist_manager.controller;

import demo.sat.tracklist_manager.controller.dto.TrackDTO;
import demo.sat.tracklist_manager.controller.mapper.TrackMapper;
import demo.sat.tracklist_manager.domain.Track;
import demo.sat.tracklist_manager.service.TrackService;
import dev.springbloom.jms.AsyncMessagePublisher;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/v1/track")
public class TrackController {

    private final TrackService trackService;

    private final AsyncMessagePublisher trackMessagePublisher;

    @Autowired
    public TrackController(
        AsyncMessagePublisher trackMessagePublisher,
        TrackService trackService
    ) {
        this.trackService = trackService;
        this.trackMessagePublisher = trackMessagePublisher;
    }

    @GetMapping
    public List<TrackDTO> fetchAllTracks() {
        List<Track> tracks = trackService.findAllTracks();
        return getTrackDTOS(tracks);
    }

    @GetMapping(value = "/{id}")
    public TrackDTO fetchTrackById(@PathVariable("id") long id) {
        Track currentTrack = trackService.findById(id);
        return TrackMapper.INSTANCE.toTrackDTO(currentTrack);
    }

    @GetMapping(value = "/name/{trackName}")
    public List<TrackDTO> fetchTrackByName(@PathVariable("trackName") String trackName) {
        List<Track> tracks = trackService.findByName(trackName);
        return getTrackDTOS(tracks);
    }

    @NotNull
    private List<TrackDTO> getTrackDTOS(List<Track> tracks) {
        if (tracks.isEmpty()) {
            return Collections.emptyList();
        }

        List<TrackDTO> trackDTOs = new ArrayList<>();
        for (Track track : tracks) {
            trackDTOs.add(TrackMapper.INSTANCE.toTrackDTO(track));
        }
        return trackDTOs;
    }

    @PostMapping
    public void createTrack(@RequestBody @Valid TrackDTO trackDTO) {
        log.info("Creating Track '{}'", trackDTO.getTrackName());

        // Send the message to the queue.
        trackMessagePublisher.publish(TrackMapper.INSTANCE.toTrack(trackDTO));
    }

    @PutMapping(value = "/{id}")
    public TrackDTO updateTrack(@PathVariable("id") Long id, @RequestBody @Valid TrackDTO trackDTO) {
        log.info("Updating Track with id '{}'", id);

        Track currentTrack = trackService.findById(id);
        TrackMapper.INSTANCE.toTrack(trackDTO, currentTrack);

        trackService.updateTrack(currentTrack);
        return TrackMapper.INSTANCE.toTrackDTO(currentTrack);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteTrack(@PathVariable("id") long id) {
        log.info("Fetching & Deleting Track with id {}", id);
        trackService.deleteTrackById(id);
    }
}
