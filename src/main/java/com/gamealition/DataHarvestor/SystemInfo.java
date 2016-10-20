package com.gamealition.DataHarvestor;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class SystemInfo {

    private Runtime runtime = Runtime.getRuntime();



    public String OSname() {
        return System.getProperty("os.name");
    }

    public String OSversion() {
        return System.getProperty("os.version");
    }

    public String OsArch() {
        return System.getProperty("os.arch");
    }

    public int totalMem() {
        return (int) Runtime.getRuntime().totalMemory();
    }

    public int usedMem() {
        return (int) (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
    }

    public double getProcessCpuLoad() throws Exception {
        MBeanServer mbs    = ManagementFactory.getPlatformMBeanServer();
        ObjectName name    = ObjectName.getInstance("java.lang:type=OperatingSystem");
        AttributeList list = mbs.getAttributes(name, new String[]{ "ProcessCpuLoad" });
        if (list.isEmpty())     return Double.NaN;
        Attribute att = (Attribute)list.get(0);
        Double value  = (Double)att.getValue();
        // usually takes a couple of seconds before we get real values
        if (value == -1.0)      return Double.NaN;
        // returns a percentage value with 1 decimal point precision
        return ((int)(value * 1000) / 10.0);
    }

}