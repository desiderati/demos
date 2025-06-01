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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import demo.sat.tracklist_manager.domain.Track;
import demo.sat.tracklist_manager.service.TrackService;
import dev.springbloom.jms.AsyncMessagePublisher;
import dev.springbloom.test.annotation.AutoConfigureSpringBloomWeb;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest // Spring test slicing.
@AutoConfigureSpringBloomWeb // This will prefix all controllers with the API's base path.
class TrackControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TrackService trackServiceMock;

    @MockBean
    private AsyncMessagePublisher trackMessagePublisher;

    private final List<Track> tracks = List.of(
        new Track("teste 1", "teste 1", 100),
        new Track("teste 2", "teste 2", 200)
    );

    @BeforeEach
    void setup() {
        Mockito.when(trackServiceMock.findByName(Mockito.contains("teste"))).thenReturn(tracks);
    }

    @Test
    void shouldFetchTracksByName() throws Exception {
        MvcResult result =
            mockMvc.perform(get("/api/v1/track/name/teste"))
                .andExpect(status().is(200)
                ).andReturn();

        String body = result.getResponse().getContentAsString();
        TypeReference<List<Track>> typeRef = new TypeReference<>() {
        };
        List<Track> returnedTracks = objectMapper.readValue(body, typeRef);

        assertThat(returnedTracks.size()).isEqualTo(2);
        assertThat(returnedTracks).contains(tracks.toArray(new Track[0]));
    }

    @Test
    void shouldNotFetchTracksByName() throws Exception {
        MvcResult result =
            mockMvc.perform(get("/api/v1/track/name/other"))
                .andExpect(status().is(200)
                ).andReturn();

        String body = result.getResponse().getContentAsString();
        TypeReference<List<Track>> typeRef = new TypeReference<>() {
        };
        List<Track> returnedTracks = objectMapper.readValue(body, typeRef);

        assertThat(returnedTracks.size()).isEqualTo(0);
    }

    // TODO: Implement other tests!
}
