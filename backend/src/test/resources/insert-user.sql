INSERT INTO public.person (name, gender, birth_date, child_num, login, password, role)
VALUES ('John Smith', 'MALE', '1990-01-01', 2, 'user1', '$2a$08$ACWkV0wxoTQ804ztx9pXu.EAeT3lNayqzMYV80yR53BCA2NBY/Kqy',
        'USER')
ON CONFLICT DO NOTHING;