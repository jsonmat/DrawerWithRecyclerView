package com.jaysontamayo.drawer.data;

import androidx.annotation.NonNull;

public class CountryModel {
    public int countryID;
    public String country;
    public String description;
    public String capital;
    public String population;
    public String image;
    public int favorite;

    public CountryModel(int countryID, String country, String description, String capital,
                        String population, String image, int favorite){
        this.countryID = countryID;
        this.country = country;
        this.description = description;
        this.capital = capital;
        this.population = population;
        this.image = image;
        this.favorite = favorite;
    }

    @NonNull
    @Override
    public String toString() {
        String s = "" + countryID + ": " + country;
        return s;
    }
}
