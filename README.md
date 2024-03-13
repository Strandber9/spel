# spel

## H2DB
[-h2-console](https://8080-strandber9-spel-1gmd9jovhqj.ws-us109.gitpod.io/h2-console)
> d위 url로 접속하여 디비데이터 확인할수 있음.
url: jdbc:h2:mem:spel
username: sa

<code>schema.sql</code>
```
DROP TABLE IF EXISTS configurations;
CREATE TABLE configurations (
    id          INTEGER AUTO_INCREMENT PRIMARY KEY,
    application VARCHAR(255),
    profile     VARCHAR(255),
    label       VARCHAR(255),
    prop_key    VARCHAR(255),
    prop_value  VARCHAR(255),
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP(6),
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)
);
```

<code>data.sql</code>
```
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
```

# REFRESH
> H2DB의 내용으로 PropertyBean Refresh -
https://8080-strandber9-spel-1gmd9jovhqj.ws-us109.gitpod.io/refresh

# STEP-01
> 현재 프로퍼티의 구성을 JSON형식으로 ㅂㅂ버여줌
https://8080-strandber9-spel-1gmd9jovhqj.ws-us109.gitpod.io/step-01

<code>Response</code>
```
{
    "mapper-id": "@RequestBody.header.ifId.replaceAll(\"/(\\w+)/.*\", \"$1\")",
    "request": {
        "header": {
            "env-dvsn-cd": "#environment.getProperty(\"spring.profiles.active\") == \"prod\" ? \"S\" : \"D\"",
            "rcptDtm": "/home/gitpod/.sdkman/candidates/java/current",
            "trmsDtm": "🎁🎁🎁",
            "usr-id": "@RequestBody.header.usrId.replace(\"@Va\", \"\")"
        }
    },
    "response": {
        "header": {
            "rcptDtm": "/home/gitpod/.sdkman/candidates/java/current",
            "trmsDtm": "🎁🎁🎁",
            "usr-id": "@RequestBody.header.usrId"
        }
    },
    "mappers": {
        "SH": {
            "mapper-id": "@RequestBody.header.ifId.replaceAll(\"/(\\w+)/(\\w+)\", \"$2\")",
            "request": {
                "header": {
                    "if-id": "MCDRCOORAO00001"
                },
                "data": {
                    "BkAccNo": "1111111111",
                    "InfoGdsGthrCnstYn": "9"
                }
            },
            "mappers": {
                "PersonalCardNew": {
                    "mapper-id": "\"U\" + @RequestBody.header.usrId.replace(\"@Va\", \"\")",
                    "request": {
                        "data": {
                            "GthrCnstYn": "@RequestBody.data.InfoGdsGthrCnstYn"
                        }
                    },
                    "response": {
                        "data": {
                            "gubun": "GG"
                        }
                    },
                    "mappers": {
                        "U055": {
                            "mapper-id": "",
                            "request": {
                                "header": {
                                    "if-id": "ECDFCDG00O00627"
                                },
                                "data": {
                                    "BkAccNo": "U055U055U055U055"
                                }
                            }
                        },
                        "U099": {
                            "mapper-id": "",
                            "request": {
                                "header": {
                                    "if-id": "ECDFCDG00O00099"
                                },
                                "data": {
                                    "BkAccNo": "U099U099U099U099"
                                }
                            },
                            "response": {
                                "data": {
                                    "javaHome": "/home/gitpod/.sdkman/candidates/java/current",
                                    "message": "👌👌👌",
                                    "resCode": "R0000"
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
```

# STEP-02
> 현재 프로퍼티의 구성을 RequestData를 참조하여 최종 API의 프로퍼티를 계산함
https://8080-strandber9-spel-1gmd9jovhqj.ws-us109.gitpod.io/step-02

<code>Request</code>
```
{
    "header": {
        "ifId": "/SH/PersonalCardNew",
        "usrId": "@Va099"
    },
    "data": {
        "InfoGdsGthrCnstYn": "N"
    }
}
```
<code>Response</code>
```
{
    "mapper-id": "",
    "request": {
        "header": {
            "env-dvsn-cd": "#environment.getProperty(\"spring.profiles.active\") == \"prod\" ? \"S\" : \"D\"",
            "rcptDtm": "/home/gitpod/.sdkman/candidates/java/current",
            "trmsDtm": "🎁🎁🎁",
            "usr-id": "@RequestBody.header.usrId.replace(\"@Va\", \"\")",
            "if-id": "ECDFCDG00O00099"
        },
        "data": {
            "BkAccNo": "U099U099U099U099",
            "InfoGdsGthrCnstYn": "9",
            "GthrCnstYn": "@RequestBody.data.InfoGdsGthrCnstYn"
        }
    },
    "response": {
        "header": {
            "rcptDtm": "/home/gitpod/.sdkman/candidates/java/current",
            "trmsDtm": "🎁🎁🎁",
            "usr-id": "@RequestBody.header.usrId"
        },
        "data": {
            "gubun": "GG",
            "javaHome": "/home/gitpod/.sdkman/candidates/java/current",
            "message": "👌👌👌",
            "resCode": "R0000"
        }
    }
}
```


# STEP-03
> H2디비의 내용으로 해당 프로퍼티에 표현식을 적용
https://8080-strandber9-spel-1gmd9jovhqj.ws-us109.gitpod.io/step-03
<code>Request</code>
```
{
    "header": {
        "ifId": "/SH/PersonalCardNew",
        "usrId": "@Va099"
    },
    "data": {
        "InfoGdsGthrCnstYn": "N"
    }
}
```

<code>Response</code>
```
{
    "mapper-id": "",
    "request": {
        "header": {
            "env-dvsn-cd": "D",
            "rcptDtm": "/home/gitpod/.sdkman/candidates/java/current",
            "trmsDtm": "🎁🎁🎁",
            "usr-id": "099",
            "if-id": "ECDFCDG00O00099"
        },
        "data": {
            "BkAccNo": "U099U099U099U099",
            "InfoGdsGthrCnstYn": "9",
            "GthrCnstYn": "N"
        }
    },
    "response": {
        "header": {
            "rcptDtm": "/home/gitpod/.sdkman/candidates/java/current",
            "trmsDtm": "🎁🎁🎁",
            "usr-id": "@Va099"
        },
        "data": {
            "gubun": "GG",
            "javaHome": "/home/gitpod/.sdkman/candidates/java/current",
            "message": "👌👌👌",
            "resCode": "R0000"
        }
    }
}
```

# SPEL
> 요청데이터를 기반으로 SPEL표현식을 테스트해볼수 있음. spel에 표현식 입력하면 됨 -
https://8080-strandber9-spel-1gmd9jovhqj.ws-us109.gitpod.io/spel

1. 환경변수는 #environment 으로 접근
2. 요청데이터는 @RequestBody로 접근

<code>Request</code>
```
{
    "spel": "#RequestBody.data",
    "header": {
        "ifId": "/SH/PersonalCardNew",
        "usrId": "ECDFCDG00O00099"
    },
    "data": {
        "message": "❤❤❤"
    }
}
```

<code>Response</code>
```
{
    "message": "❤❤❤"
}
```

<code>Request</code>
```
{
    "spel": "#environment.getProperty('JAVA_HOME')",
    "header": {
        "ifId": "/SH/PersonalCardNew",
        "usrId": "${JAVA_HOME}"
    }
}
```

<code>Response</code>
```
/home/xxxxx/.sdkman/candidates/java/current
```