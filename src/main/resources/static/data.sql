/*
DROP TABLE address CASCADE;
DROP TABLE language CASCADE;
DROP TABLE doctor CASCADE;
DROP TABLE doctor_addresses CASCADE;
drop table doctor_languages cascade;
-- DROP TABLE doctor_opening_hours CASCADE;
DROP TABLE doctor_specialities CASCADE;
-- DROP TABLE doctor_work_schedule CASCADE;
DROP TABLE country CASCADE;
DROP TABLE opening_hours CASCADE;
DROP TABLE patient CASCADE;
DROP TABLE patient_addresses CASCADE;
DROP TABLE doctor_educations CASCADE;
DROP TABLE education CASCADE;
DROP TABLE university CASCADE;
DROP TABLE work_schedule CASCADE;
-- DROP TABLE work_schedule_opening_hours CASCADE;
*/

INSERT INTO public.speciality (id, code, value) VALUES (1, 'PNLG', 'Pneumologue');

INSERT INTO public.doctor (id, firstname, gender, lastname, surname, phone, email, availability, birthday, overview) VALUES (1, 'Mohamed', 'MALE', 'STOULI', null, '05 35 40 45 25', null, null, null, null);
INSERT INTO public.doctor (id, firstname, gender, lastname, surname, phone, email, availability, birthday, overview) VALUES (2, 'Hamza', 'MALE', 'KHAFIF', null, null, null, null, null, null);

INSERT INTO public.work_schedule (id, description, doctor_id) VALUES (1, 'Full-time', 1);

INSERT INTO public.opening_hours (id, closing_time, day, opening_time, opens, work_schedule_id) VALUES (1, '08:00:00', 'MONDAY', '18:00:00', true, 1);
INSERT INTO public.opening_hours (id, closing_time, day, opening_time, opens, work_schedule_id) VALUES (2, '08:00:00', 'TUESDAY', '18:00:00', true, 1);
INSERT INTO public.opening_hours (id, closing_time, day, opening_time, opens, work_schedule_id) VALUES (3, '08:00:00', 'WEDNESDAY', '18:00:00', true, 1);
INSERT INTO public.opening_hours (id, closing_time, day, opening_time, opens, work_schedule_id) VALUES (4, '08:00:00', 'THURSDAY', '18:00:00', true, 1);
INSERT INTO public.opening_hours (id, closing_time, day, opening_time, opens, work_schedule_id) VALUES (5, '08:00:00', 'FRIDAY', '18:00:00', true, 1);
INSERT INTO public.opening_hours (id, closing_time, day, opening_time, opens, work_schedule_id) VALUES (7, null, 'SUNDAY', null, false, 1);
INSERT INTO public.opening_hours (id, closing_time, day, opening_time, opens, work_schedule_id) VALUES (6, null, 'SATURDAY', null, false, 1);

COMMIT;