create type gen as enum ('male', 'female');

create table if not exists person
(
    id         bigint generated always as identity
        primary key,
    name       varchar                                   not null,
    gender     varchar                                   not null
        constraint person_gender_check
            check (((gender)::text = 'male'::text) OR ((gender)::text = 'female'::text)),
    birth_date date                                      not null,
    child_num  integer                                   not null
        constraint person_child_num_check
            check (child_num >= 0),
    login      varchar                                   not null
        unique
        constraint person_login_check
            check ((length((login)::text) >= 3) AND (length((login)::text) <= 16)),
    password   varchar                                   not null,
    role       varchar default 'user'::character varying not null
        constraint person_role_check
            check (((role)::text = 'user'::text) OR ((role)::text = 'admin'::text))
);

create table if not exists subject
(
    id           bigint generated always as identity
        primary key,
    name         varchar not null
        unique,
    control_form varchar not null
        constraint subject_control_form_check
            check (((control_form)::text = 'exam'::text) OR ((control_form)::text = 'credit'::text))
);

create table if not exists curriculum
(
    id               bigint generated always as identity
        primary key,
    subject_id       bigint
        references subject,
    lecture_hours    integer
        constraint curriculum_lecture_hours_check
            check (lecture_hours >= 0),
    seminar_hours    integer
        constraint curriculum_seminar_hours_check
            check (seminar_hours >= 0),
    laboratory_hours integer
        constraint curriculum_laboratory_hours_check
            check (laboratory_hours >= 0),
    consult_hours    integer
        constraint curriculum_consult_hours_check
            check (consult_hours >= 0),
    coursework_hours integer,
    individual_hours integer
        constraint curriculum_individual_hours_check
            check (individual_hours >= 0),
    semesters        integer[] not null
);

create table if not exists faculty
(
    id   integer generated always as identity
        primary key,
    name varchar not null
        unique
);

create table if not exists department
(
    id         bigint generated always as identity
        primary key,
    faculty_id integer not null
        references faculty,
    name       varchar not null
        unique
);

create table if not exists teacher
(
    person_id     bigint  not null
        primary key
        references person,
    department_id bigint
        references department,
    post          varchar not null
        constraint teacher_post_check
            check (((post)::text = 'assistant'::text) OR ((post)::text = 'teacher'::text) OR
                   ((post)::text = 'senior teacher'::text) OR ((post)::text = 'docent'::text) OR
                   ((post)::text = 'professor'::text)),
    salary        numeric(9, 3)
        constraint teacher_salary_check
            check (salary >= (0)::numeric)
);

create table if not exists "group"
(
    id         bigint generated always as identity
        primary key,
    faculty_id integer  not null
        references faculty,
    name       varchar  not null
        unique,
    course     smallint not null
        constraint group_course_check
            check (course > 0)
);

create table if not exists student
(
    person_id       bigint  not null
        primary key
        references person,
    group_id        bigint  not null
        references "group",
    education_level varchar not null
        constraint student_education_level_check
            check (((education_level)::text = 'bachelor'::text) OR ((education_level)::text = 'master'::text) OR
                   ((education_level)::text = 'postgraduate'::text)),
    scholarship     numeric(7, 2),
    last_modified   date
);

create table if not exists graduate_work
(
    teacher_id bigint  not null
        references teacher,
    name       varchar not null,
    id         bigint generated always as identity,
    student_id bigint  not null
        constraint graduate_work_student_id
            references student
);

create table if not exists records_book
(
    id         bigint generated always as identity
        primary key,
    student_id bigint   not null
        references student,
    subject_id bigint   not null
        references subject,
    semester   smallint not null
        constraint records_book_semester_check
            check (semester > 0),
    date       date     not null,
    mark       varchar  not null
        constraint records_book_mark_check
            check (((mark)::text = '2'::text) OR ((mark)::text = '3'::text) OR ((mark)::text = '4'::text) OR
                   ((mark)::text = '5'::text) OR ((mark)::text = 'passed'::text) OR ((mark)::text = 'failed'::text))
);

create table if not exists class
(
    id          bigint generated always as identity
        primary key,
    teacher_id  bigint
        references teacher,
    subject_id  bigint
        references subject,
    group_id    bigint
        references "group",
    type        varchar not null
        constraint class_type_check
            check (((type)::text = 'lecture'::text) OR ((type)::text = 'seminar'::text) OR
                   ((type)::text = 'laboratory'::text) OR ((type)::text = 'consultation'::text) OR
                   ((type)::text = 'coursework'::text) OR ((type)::text = 'individual'::text)),
    start_date  date    not null,
    finish_date date    not null
);

create table if not exists dissertation
(
    id         bigint generated always as identity
        primary key,
    teacher_id bigint
        references teacher,
    name       varchar not null,
    type       varchar not null
        constraint dissertation_type_check
            check (((type)::text = 'PhD'::text) OR ((type)::text = 'doctoral'::text)),
    date       date    not null
);