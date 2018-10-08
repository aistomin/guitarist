package com.github.aistomin.guitarist.midi;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

/**
 * Created by aistomin on 08.10.18.
 * <p>
 * Our small limited MIDI player to play notes.
 */
public final class MidiPlayer {

    /**
     * MIDI channel.
     */
    private final MidiChannel channel;

    /**
     * Ctor.
     *
     * @throws MidiUnavailableException If system synthesizer is not available.
     */
    public MidiPlayer() throws MidiUnavailableException {
        final Synthesizer synth = MidiSystem.getSynthesizer();
        synth.open();
        synth.loadInstrument(synth.getDefaultSoundbank().getInstruments()[0]);
        this.channel = synth.getChannels()[0];
    }

    /**
     * Play the provided note with the provided velocity.
     *
     * @param note     The note.
     * @param velocity The velocity.
     */
    public void play(final Integer note, final Integer velocity) {
        this.channel.noteOn(note, velocity);
        try {
            Thread.sleep(1000);
        } catch (final InterruptedException ex) {
            throw new RuntimeException("InterruptedException", ex);
        }
    }
}
