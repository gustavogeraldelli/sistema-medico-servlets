INSERT INTO usuarios (email, senha, tipo) VALUES
    ("admin@admin.com", "admin", 1),
    ("oftalmo@medico.com", "medico", 2),
    ("otorrino@medico.com", "medico", 2),
    ("neuro@medico.com", "medico", 2),
    ("cardio@medico.com", "medico", 2),
    ("dermato@medico.com", "medico", 2),
    ("paciente1@paciente.com", "paciente", 3),
    ("paciente2@paciente.com", "paciente", 3),
    ("paciente3@paciente.com", "paciente", 3),
    ("paciente4@paciente.com", "paciente", 3),
    ("paciente5@paciente.com", "paciente", 3);

INSERT INTO medicos (id_usuario, crm, nome, especialidade) VALUES
    (2, "12345", "João Silva", "Oftalmologia"),
    (3, "54321", "Maria Souza", "Otorrinolaringologia"),
    (4, "98765", "Carlos Oliveira", "Neurologia"),
    (5, "56789", "José Cardoso", "Cardiologia"),
    (6, "45678", "Ana Costa", "Dermatologia");

INSERT INTO pacientes (id_usuario, cpf, nome, telefone, sexo, data_nasc) VALUES
    (7, "12345678900", "João da Silva", "123456789", "M", "1990-01-01"),
    (8, "98765432100", "Maria Oliveira", "987654321", "F", "1985-05-05"),
    (9, "11122233300", "Carlos Souza", "111222333", "M", "1988-08-08"),
    (10, "44455566600", "Ana Santos", "444555666", "F", "1995-12-12"),
    (11, "77788899900", "José Costa", "777888999", "M", "2000-06-15");

INSERT INTO consultas (id_paciente, id_medico, data_hora) VALUES
    (1, 1, '2024-07-15 09:00:00'),
    (1, 2, '2024-08-02 10:00:00'),
    (2, 3, '2024-07-14 11:00:00'),
    (2, 4, '2024-07-25 13:00:00'),
    (3, 5, '2024-07-10 14:00:00'),
    (3, 1, '2024-08-09 09:30:00'),
    (4, 2, '2024-07-16 10:30:00'),
    (4, 3, '2024-07-24 11:30:00'),
    (5, 4, '2024-07-06 13:30:00'),
    (5, 5, '2024-07-26 14:30:00');