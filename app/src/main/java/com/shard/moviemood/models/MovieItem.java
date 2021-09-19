package com.shard.moviemood.models;

public class MovieItem
{
    private String mId;
    private String mTitle;
    private double mRating;
    private String mDate;
    private String mPlot;
    private String mImagePath;

    public String getId()
    {
        return mId;
    }

    public void setId(String id)
    {
        mId = id;
    }

    public String getTitle()
    {
        return mTitle;
    }

    public void setTitle(String title)
    {
        mTitle = title;
    }

    public double getRating()
    {
        return mRating;
    }

    public void setRating(double rating)
    {
        mRating = rating;
    }

    public String getDate()
    {
        return mDate;
    }

    public void setDate(String date)
    {
        mDate = date;
    }

    public String getPlot()
    {
        return mPlot;
    }

    public void setPlot(String plot)
    {
        mPlot = plot;
    }

    public String getImagePath()
    {
        return mImagePath;
    }

    public void setImagePath(String path)
    {
        mImagePath = "https://image.tmdb.org/t/p/original/" + path;
    }
}
