# PianoChordLogger
Detect and display chord played by user by processing incoming spectrum and save chords accepted by user to a text file.

Technologies:
- Swing, NetBeans IDE (jdk-8u131-nb-8_2)
- jSSC-2.8.0 (scream3r)

Functionality:
- display the strongest-noticed Notes and their strength
- recognize Chord if the 3 strongest Notes are its component part
- add/remove Chord from the list of last saved Chords
- save Chords to a text file
- configure serial communication parameters

<p align="justify">Note: in order to add/remove last-recognized Chord from the list user should play specific Chords (which are normally invalid but here they are of great value), C+CIS+D and DIS+E+F respectively.</p>

## Serial data decoding
<p align="justify">The implementation of SerialPortEventListener allows the app to transform data from serial port to an array using specified frame. Such a pattern includes n numbers (in this case 256) with separator between them (' ') and between n-number data packets ('s').</p>

## Note decoding
<p align="justify">Notes are recognized from the incoming data representing signal strength for particular radio frequencies from 0 up to 1000 Hz. The signal frequency which amplitude exceeds the threshold value is treated as a valid note signal. Next it is compared with values from the note list so as to check its Octave and Pich. The algorithm allow for averaging if necessary.</p>

## Chord decoding
<p align="justify">From the array of recognized Notes, app compares the combination of three loudest to every Chord contained in the list of valid combinations. If (and only if) they are matched, app informs user that the valid Chord is found.</p>
