package net.etfbl.rss;

import java.io.Serializable;

public class FeedMessage implements Serializable
{
	private static final long serialVersionUID = 1L;
	String title;
	String description;
	String link;
	String author;
	String guid;
	String image;
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public String getLink()
	{
		return link;
	}
	
	public void setLink(String link)
	{
		this.link = link;
	}
	
	public String getAuthor()
	{
		return author;
	}
	
	public void setAuthor(String author)
	{
		this.author = author;
	}
	
	public String getGuid()
	{
		return guid;
	}
	
	public void setGuid(String guid)
	{
		this.guid = guid;
	}
	
	public String getImage()
	{
		return image;
	}

	public void setImage(String image)
	{
		this.image = image;
	}

	@Override
	public String toString()
	{
		return "FeedMessage [title=" + title + ", description=" + description
				+ ", link=" + link + ", author=" + author + ", guid=" + guid
				+ "]";
	}
}
