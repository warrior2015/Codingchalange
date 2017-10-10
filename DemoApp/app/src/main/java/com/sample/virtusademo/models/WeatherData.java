package com.sample.virtusademo.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *
 * {
 *   "coord":{"lon":-122.08,"lat":37.39},
 *   "sys":{
 *           "type":1,"id":451,"message":1.1091,
 *           "country":"United States of America",
 *           "sunrise":1434545231,"sunset":1434598293
 *         },
 *   "weather":[{"id":701,"main":"Mist","description":"mist","icon":"50n"}],
 *   "base":"stations",
 *   "main":{"temp":288.73,"pressure":1014,"humidity":82,"temp_min":284.26,"temp_max":294.15},
 *   "visibility":16093,
 *   "wind":{"speed":4.1,"deg":350},
 *   "clouds":{"all":20},
 *   "dt":1434517063,
 *   "id":5375480,
 *   "name":"Mountain View",
 *   "cod":200
 * }
 */
public  class WeatherData implements Parcelable {

    @SerializedName("coord")
    @Expose
    public Coord coord;
    @SerializedName("weather")
    @Expose
    public List<Weather> weather = null;
    @SerializedName("base")
    @Expose
    public String base;
    @SerializedName("main")
    @Expose
    public Main main;
    @SerializedName("visibility")
    @Expose
    public Integer visibility;
    @SerializedName("wind")
    @Expose
    public Wind wind;
    @SerializedName("clouds")
    @Expose
    public Clouds clouds;
    @SerializedName("dt")
    @Expose
    public Integer dt;
    @SerializedName("sys")
    @Expose
    public Sys sys;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("cod")
    @Expose
    public Integer cod;
    public final static Parcelable.Creator<WeatherData> CREATOR = new Parcelable.Creator<WeatherData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public WeatherData createFromParcel(Parcel in) {
            return new WeatherData(in);
        }

        public WeatherData[] newArray(int size) {
            return (new WeatherData[size]);
        }

    }
            ;

    protected WeatherData(Parcel in) {
        this.coord = ((Coord) in.readValue((Coord.class.getClassLoader())));
        in.readList(this.weather, (Weather.class.getClassLoader()));
        this.base = ((String) in.readValue((String.class.getClassLoader())));
        this.main = ((Main) in.readValue((Main.class.getClassLoader())));
        this.visibility = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.wind = ((Wind) in.readValue((Wind.class.getClassLoader())));
        this.clouds = ((Clouds) in.readValue((Clouds.class.getClassLoader())));
        this.dt = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.sys = ((Sys) in.readValue((Sys.class.getClassLoader())));
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.cod = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public WeatherData() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(coord);
        dest.writeList(weather);
        dest.writeValue(base);
        dest.writeValue(main);
        dest.writeValue(visibility);
        dest.writeValue(wind);
        dest.writeValue(clouds);
        dest.writeValue(dt);
        dest.writeValue(sys);
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(cod);
    }

    public int describeContents() {
        return 0;
    }

    public Coord getCoord() {
        return coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public String getBase() {
        return base;
    }

    public Main getMain() {
        return main;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public Integer getDt() {
        return dt;
    }

    public Sys getSys() {
        return sys;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getCod() {
        return cod;
    }


    public static class Main implements Parcelable
    {
        public Float getTemp() {
            return temp;
        }

        public Integer getPressure() {
            return pressure;
        }

        public Integer getHumidity() {
            return humidity;
        }

        public Float getTempMin() {
            return tempMin;
        }

        public Float getTempMax() {
            return tempMax;
        }

        @SerializedName("temp")
        @Expose
        public Float temp;
        @SerializedName("pressure")
        @Expose
        public Integer pressure;
        @SerializedName("humidity")
        @Expose
        public Integer humidity;
        @SerializedName("temp_min")
        @Expose
        public Float tempMin;
        @SerializedName("temp_max")
        @Expose
        public Float tempMax;
        public final static Parcelable.Creator<Main> CREATOR = new Creator<Main>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Main createFromParcel(Parcel in) {
                return new Main(in);
            }

            public Main[] newArray(int size) {
                return (new Main[size]);
            }

        }
                ;

        protected Main(Parcel in) {
            this.temp = ((Float) in.readValue((Float.class.getClassLoader())));
            this.pressure = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.humidity = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.tempMin = ((Float) in.readValue((Float.class.getClassLoader())));
            this.tempMax = ((Float) in.readValue((Float.class.getClassLoader())));
        }

        public Main() {
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(temp);
            dest.writeValue(pressure);
            dest.writeValue(humidity);
            dest.writeValue(tempMin);
            dest.writeValue(tempMax);
        }

        public int describeContents() {
            return 0;
        }

    }


    class Coord {
        double lat;
        double lon;

        public Coord(double lat, double lon) {
            this.lat = lat;
            this.lon = lon;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLon() {
            return lon;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }
    }

    public static class Weather implements Parcelable
    {
        public Integer getId() {
            return id;
        }

        public String getMain() {
            return main;
        }

        public String getDescription() {
            return description;
        }

        public String getIcon() {
            return icon;
        }

        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("main")
        @Expose
        public String main;
        @SerializedName("description")
        @Expose
        public String description;
        @SerializedName("icon")
        @Expose
        public String icon;
        public final static Parcelable.Creator<Weather> CREATOR = new Creator<Weather>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Weather createFromParcel(Parcel in) {
                return new Weather(in);
            }

            public Weather[] newArray(int size) {
                return (new Weather[size]);
            }

        }
                ;

        protected Weather(Parcel in) {
            this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.main = ((String) in.readValue((String.class.getClassLoader())));
            this.description = ((String) in.readValue((String.class.getClassLoader())));
            this.icon = ((String) in.readValue((String.class.getClassLoader())));
        }

        public Weather() {
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(main);
            dest.writeValue(description);
            dest.writeValue(icon);
        }

        public int describeContents() {
            return 0;
        }

    }
    public static class Sys implements Parcelable
    {
        public Integer getType() {
            return type;
        }

        public Integer getId() {
            return id;
        }

        public Float getMessage() {
            return message;
        }

        public String getCountry() {
            return country;
        }

        public Integer getSunrise() {
            return sunrise;
        }

        public Integer getSunset() {
            return sunset;
        }

        @SerializedName("type")
        @Expose
        public Integer type;
        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("message")
        @Expose
        public Float message;
        @SerializedName("country")
        @Expose
        public String country;
        @SerializedName("sunrise")
        @Expose
        public Integer sunrise;
        @SerializedName("sunset")
        @Expose
        public Integer sunset;
        public final static Parcelable.Creator<Sys> CREATOR = new Creator<Sys>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Sys createFromParcel(Parcel in) {
                return new Sys(in);
            }

            public Sys[] newArray(int size) {
                return (new Sys[size]);
            }

        }
                ;

        protected Sys(Parcel in) {
            this.type = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.message = ((Float) in.readValue((Float.class.getClassLoader())));
            this.country = ((String) in.readValue((String.class.getClassLoader())));
            this.sunrise = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.sunset = ((Integer) in.readValue((Integer.class.getClassLoader())));
        }

        public Sys() {
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(type);
            dest.writeValue(id);
            dest.writeValue(message);
            dest.writeValue(country);
            dest.writeValue(sunrise);
            dest.writeValue(sunset);
        }

        public int describeContents() {
            return 0;
        }

    }


    // Accesor methods
    public double getLat() {
        return coord.getLat();
    }

    public double getLon() {
        return coord.getLon();
    }





    public static class Clouds implements Parcelable
    {
        public Integer getAll() {
            return all;
        }

        @SerializedName("all")
        @Expose
        public Integer all;
        public final static Parcelable.Creator<Clouds> CREATOR = new Creator<Clouds>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Clouds createFromParcel(Parcel in) {
                return new Clouds(in);
            }

            public Clouds[] newArray(int size) {
                return (new Clouds[size]);
            }

        }
                ;

        protected Clouds(Parcel in) {
            this.all = ((Integer) in.readValue((Integer.class.getClassLoader())));
        }

        public Clouds() {
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(all);
        }

        public int describeContents() {
            return 0;
        }

    }

    public static class Wind implements Parcelable
    {
        public Float getSpeed() {
            return speed;
        }

        public Integer getDeg() {
            return deg;
        }

        @SerializedName("speed")
        @Expose
        public Float speed;
        @SerializedName("deg")
        @Expose
        public Integer deg;
        public final static Parcelable.Creator<Wind> CREATOR = new Creator<Wind>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Wind createFromParcel(Parcel in) {
                return new Wind(in);
            }

            public Wind[] newArray(int size) {
                return (new Wind[size]);
            }

        }
                ;

        protected Wind(Parcel in) {
            this.speed = ((Float) in.readValue((Float.class.getClassLoader())));
            this.deg = ((Integer) in.readValue((Integer.class.getClassLoader())));
        }

        public Wind() {
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(speed);
            dest.writeValue(deg);
        }

        public int describeContents() {
            return 0;
        }

    }
}
