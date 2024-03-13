# spel

## H2DB
[-h2-console](https://8080-strandber9-spel-1gmd9jovhqj.ws-us109.gitpod.io/h2-console)
url: jdbc:h2:mem:spel
username: sa

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