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
import {Component, OnInit} from '@angular/core';

import {ActivatedRoute, RouterLink} from '@angular/router';
import {NgIf} from '@angular/common';

import {Track} from '../track';
import {TrackService} from '../track.service';

@Component({
    selector: 'app-track-details',
    templateUrl: './track-details.component.html',
    imports: [
        NgIf,
        RouterLink
    ],
    styleUrls: ['./track-details.component.css']
})
export class TrackDetailsComponent implements OnInit {
    track: Track | undefined;

    constructor(
        private route: ActivatedRoute,
        private trackService: TrackService
    ) {
    }

    ngOnInit(): void {
        this.fetchTrackById();
    }

    fetchTrackById(): void {
        const id = Number(this.route.snapshot.paramMap.get('id'));
        this.trackService.fetchTrackById(id).subscribe(
            (track: Track) => this.track = track
        );
    }
}
