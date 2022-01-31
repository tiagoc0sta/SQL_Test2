package com.trios;

import javax.persistence.*;

@Entity
@Table(name="track")
public class Track {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "TrackId", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int trackId;

    @Column(name = "Name")
    private String Name;

    @Column(name = "AlbumId")
    private int AlbumId;

    @Column(name = "MediaTypeId")
    private int MediaTypeId;

    @Column(name = "GenreId")
    private int GenreId;

    @Column(name = "Composer")
    private String Composer;

    @Column(name = "Milliseconds")
    private int Milliseconds;

    @Column(name = "Bytes")
    private int Bytes;

    @Column(name = "UnitPrice")
    private double UnitPrice;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAlbumId() {
        return AlbumId;
    }

    public void setAlbumId(int albumId) {
        AlbumId = albumId;
    }

    public int getMediaTypeId() {
        return MediaTypeId;
    }

    public void setMediaTypeId(int mediaTypeId) {
        MediaTypeId = mediaTypeId;
    }

    public int getGenreId() {
        return GenreId;
    }

    public void setGenreId(int genreId) {
        GenreId = genreId;
    }

    public String getComposer() {
        return Composer;
    }

    public void setComposer(String composer) {
        Composer = composer;
    }

    public int getMilliseconds() {
        return Milliseconds;
    }

    public void setMilliseconds(int milliseconds) {
        Milliseconds = milliseconds;
    }

    public int getBytes() {
        return Bytes;
    }

    public void setBytes(int bytes) {
        Bytes = bytes;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        UnitPrice = unitPrice;
    }
}