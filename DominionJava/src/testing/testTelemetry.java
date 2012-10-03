package testing;

import monitoring.Telemetry;

public class testTelemetry
{
    public static void main(String[] args)
    {
        try
        {
            System.out.println("Starting main() - telemetry test...");
            Telemetry testTelemetry = new Telemetry();
            testTelemetry.addTelemetry("First entry");
            Thread.sleep(1000l);
            testTelemetry.addTelemetry("second entry 5sec later");
            Thread.sleep(1500l);
            testTelemetry.addTelemetry("Third very very very long string written that should be over 50car length");
            testTelemetry.addTelemetry("Fourth instant entry");
            Thread.sleep(25l);
            testTelemetry.addTelemetry("Small burst25");
            Thread.sleep(9l);
            testTelemetry.addTelemetry("Small burst9");
            Thread.sleep(15l);
            testTelemetry.addTelemetry("Small burst15");
            System.out.println(testTelemetry.toString());
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
