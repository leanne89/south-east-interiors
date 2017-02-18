package content;

public class Review extends Form
{
    private String name;

    private String town;

    private String county;

    private String testimonial;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getTown()
    {
        return town;
    }

    public void setTown(String town)
    {
        this.town = town;
    }

    public String getCounty()
    {
        return county;
    }

    public void setCounty(String county)
    {
        this.county = county;
    }

    public String getTestimonial()
    {
        return testimonial;
    }

    public void setTestimonial(String testimonial)
    {
        this.testimonial = testimonial;
    }

}
