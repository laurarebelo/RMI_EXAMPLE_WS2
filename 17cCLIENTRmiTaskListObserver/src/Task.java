import java.io.Serializable;

public class Task implements Serializable
{
  private String text;
  private long estimatedTime;



  public Task(String text, long estimatedTime) {
    this.text = text;
    this.estimatedTime = estimatedTime;
  }

  @Override public String toString()
  {
    return "Task{" + "text='" + text + '\'' + ", estimatedTime=" + estimatedTime
        + '}';
  }

  public String getText()
  {
    return text;
  }

  public void setText(String text)
  {
    this.text = text;
  }

  public long getEstimatedTime()
  {
    return estimatedTime;
  }

  public void setEstimatedTime(long estimatedTime)
  {
    this.estimatedTime = estimatedTime;
  }
}
