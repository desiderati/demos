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
package demo.sat.tracklist_manager.service;

import demo.sat.tracklist_manager.domain.Track;
import demo.sat.tracklist_manager.repository.TrackRepository;
import dev.springbloom.core.exception.IllegalArgumentApplicationException;
import dev.springbloom.core.exception.ResourceNotFoundApplicationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Objects;

@Service
@Validated
@Transactional
public class TrackService {

    private final TrackRepository trackRepository;

    public TrackService(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    public void saveTrack(Track track) {
        // In this example, it's not possible to have tracks with duplicate names — even if they belong
        // to different authors.

        // This wouldn't be the case in a real-world application, where such a restriction wouldn’t make sense
        // and would likely be handled differently.

        // The main goal of this validation is simply to test whether the listener correctly notifies
        // the front-end in case of an error.
        List<Track> existingTracks = findByName(track.getTrackName());
        if (existingTracks.isEmpty() || Objects.equals(existingTracks.getFirst().getId(), track.getId())) {
            trackRepository.save(track);
        } else {
            throw new IllegalArgumentApplicationException("track_name_already_exists");
        }
    }

    public void updateTrack(Track track) {
        trackRepository.save(track);
    }

    public void deleteTrackById(Long id) {
        findById(id); // Just to validate!
        trackRepository.deleteById(id);
    }

    public Track findById(Long id) {
        return trackRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundApplicationException("track_not_found_exception", id)
        );
    }

    public List<Track> findByName(String trackName) {
        return trackRepository.findByTrackNameContaining(trackName);
    }

    public List<Track> findAllTracks() {
        return trackRepository.findAll(Sort.by("trackName"));
    }
}
