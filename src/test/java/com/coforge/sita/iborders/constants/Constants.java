package com.coforge.sita.iborders.constants;

import java.io.File;

public class Constants {
	
	/**
     * Private constructor to stop things creating Contants objects.
     */
    private Constants() {
        super();
    }
	
	public static final int TIMEOUT_TWO_SEC = 2;
	public static final int TIMEOUT_FIVE_SEC = 5;
	public static final int TIMEOUT_TWO_MILISEC = 2000;
	public static final int TIMEOUT_FIVE_MILISEC = 5000;
	
    /** //src//test//resources//. */
    public static final String GLOBAL_RESOURCES_FOLDER = File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator;
    /** //src//test//resources//. */
    public static final String GLOBAL_RESOURCES_FOLDER_CONFIG = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator;
    /** //src//test//resources//data//. */
    public static final String GLOBAL_DATA_FOLDER = File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "data" + File.separator;
	

}
