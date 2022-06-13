package com.doctorspolis.backend.utility.constants;

public class DoctorspolisConstants {

    public static final String T = "T";

    public static final String EMPTY = "";

    public static final String URL_SEPARATOR = "/";

    public static final String CODE_PATH_VARIABLE = "{code}";

    public static final String SEARCH = "search";


    public static final String ID = "ID";

    public static final String ID_PATH_VARIABLE = "{ID}";

    public static final String DOCTORS = "doctors";


    public static final String PATIENTS = "patients";

    public static final String PATIENT_ID_PATH_VARIABLE = "{patientID}";

    public static final String DOCTOR_ID_PATH_VARIABLE = "{doctorID}";

    public static final String DOCTORS_WORK_SCHEDULE = DOCTORS + URL_SEPARATOR + DOCTOR_ID_PATH_VARIABLE + URL_SEPARATOR + "workSchedule";

    public static final String REFERENTIAL = "referential";

    public static final String COUNTRIES = "countries";

    public static final String LANGUAGES = "languages";

    public static final String SPECIALITIES = "specialities";

    public static final String COUNTRY_BY_CODE  = COUNTRIES + URL_SEPARATOR + CODE_PATH_VARIABLE;

    public static final String LANGUAGE_BY_CODE  = LANGUAGES + URL_SEPARATOR + CODE_PATH_VARIABLE;

    public static final String SPECIALITY_BY_CODE  = SPECIALITIES + URL_SEPARATOR + CODE_PATH_VARIABLE;

}
