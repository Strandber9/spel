
INSERT INTO configurations (application, profile, label, prop_key, prop_value)
VALUES 
    -- ROOT-DEPTH
    ('myapp', 'dev', 'main', 'apps.api.mapper-id', '@RequestBody.header.ifId.replaceAll("/(\w+)/.*", "$1")'),
    ('myapp', 'dev', 'main', 'apps.api.request.header.usr-id', '@RequestBody.header.usrId.replace("@Va", "")'),
    ('myapp', 'dev', 'main', 'apps.api.request.header.trmsDtm', '🎁🎁🎁'),
    ('myapp', 'dev', 'main', 'apps.api.request.header.rcptDtm', '${JAVA_HOME}'),
    ('myapp', 'dev', 'main', 'apps.api.request.header.env-dvsn-cd', '#environment.getProperty("spring.profiles.active") == "prod" ? "S" : "D"'),

    ('myapp', 'dev', 'main', 'apps.api.response.header.usr-id', '@RequestBody.header.usrId'),
    ('myapp', 'dev', 'main', 'apps.api.response.header.trmsDtm', '🎁🎁🎁'),
    ('myapp', 'dev', 'main', 'apps.api.response.header.rcptDtm', '${JAVA_HOME}'),

    -- SH
    ('myapp', 'dev', 'main', 'apps.api.mappers.SH.mapper-id', '@RequestBody.header.ifId.replaceAll("/(\w+)/(\w+)", "$2")'),
    -- ('myapp', 'dev', 'main', 'apps.api.mappers.SH.mapper-id', '"PersonalCardNew"'),
    ('myapp', 'dev', 'main', 'apps.api.mappers.SH.request.header.if-id', 'MCDRCOORAO00001'),
    ('myapp', 'dev', 'main', 'apps.api.mappers.SH.request.data.BkAccNo', '1111111111'),
    ('myapp', 'dev', 'main', 'apps.api.mappers.SH.request.data.InfoGdsGthrCnstYn', '9'),

    -- SH-PersonalCardNew
    ('myapp', 'dev', 'main', 'apps.api.mappers.SH.mappers.PersonalCardNew.mapper-id', '"U" + @RequestBody.header.usrId.replace("@Va", "")'),
    -- ('myapp', 'dev', 'main', 'apps.api.mappers.SH.mappers.PersonalCardNew.mapper-id', '"U055"'),
    ('myapp', 'dev', 'main', 'apps.api.mappers.SH.mappers.PersonalCardNew.request.data.GthrCnstYn', '@RequestBody.data.InfoGdsGthrCnstYn'),
    ('myapp', 'dev', 'main', 'apps.api.mappers.SH.mappers.PersonalCardNew.response.data.gubun', 'GG'),

    -- SH-PersonalCardNew-U055
    ('myapp', 'dev', 'main', 'apps.api.mappers.SH.mappers.PersonalCardNew.mappers.U055.mapper-id', ''),
    ('myapp', 'dev', 'main', 'apps.api.mappers.SH.mappers.PersonalCardNew.mappers.U055.request.header.if-id', 'ECDFCDG00O00627'),
    ('myapp', 'dev', 'main', 'apps.api.mappers.SH.mappers.PersonalCardNew.mappers.U055.request.data.BkAccNo', 'U055U055U055U055'),

    -- SH-PersonalCardNew-U099
    ('myapp', 'dev', 'main', 'apps.api.mappers.SH.mappers.PersonalCardNew.mappers.U099.mapper-id', ''),
    ('myapp', 'dev', 'main', 'apps.api.mappers.SH.mappers.PersonalCardNew.mappers.U099.request.header.if-id', 'ECDFCDG00O00099'),
    ('myapp', 'dev', 'main', 'apps.api.mappers.SH.mappers.PersonalCardNew.mappers.U099.request.data.BkAccNo', 'U099U099U099U099'),
    ('myapp', 'dev', 'main', 'apps.api.mappers.SH.mappers.PersonalCardNew.mappers.U099.response.data.message', '👌👌👌'),
    ('myapp', 'dev', 'main', 'apps.api.mappers.SH.mappers.PersonalCardNew.mappers.U099.response.data.resCode', 'R0000'),
    ('myapp', 'dev', 'main', 'apps.api.mappers.SH.mappers.PersonalCardNew.mappers.U099.response.data.javaHome', '${JAVA_HOME}'),


    ('myapp', 'test', 'main', 'END', 'END');