
INSERT INTO configurations (application, profile, label, prop_key, prop_value)
VALUES 
    ('myapp', 'dev', 'main', 'apps.api.name', '${spring.application.name}'),
    ('myapp', 'dev', 'main', 'apps.api.age', 99),
    ('myapp', 'dev', 'main', 'apps.api.message', '🎁🎁🎁'),
    ('myapp', 'dev', 'main', 'apps.api.test', '${JAVA_HOME}');