
CREATE TABLE `abType` (
                          `typeSeq` bigint NOT NULL AUTO_INCREMENT,
                          `typeName` varchar(45) COLLATE utf8mb4_german2_ci NOT NULL DEFAULT 'null',
                          `updtTimestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          `userSeq` bigint NOT NULL,
                          `abSeq` varchar(45) COLLATE utf8mb4_german2_ci NOT NULL,
                          PRIMARY KEY (`typeSeq`),
                          UNIQUE KEY `typeName_UNIQUE` (`typeName`),
                          UNIQUE KEY `abSeq_UNIQUE` (`abSeq`)
) ENGINE=InnoDB AUTO_INCREMENT=189 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;


CREATE TABLE `abType` (
                          `typeSeq` bigint NOT NULL AUTO_INCREMENT,
                          `typeName` varchar(45) COLLATE utf8mb4_german2_ci NOT NULL DEFAULT 'null',
                          `updtTimestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          `userSeq` bigint NOT NULL,
                          `abSeq` varchar(45) COLLATE utf8mb4_german2_ci NOT NULL,
                          PRIMARY KEY (`typeSeq`),
                          UNIQUE KEY `typeName_UNIQUE` (`typeName`),
                          UNIQUE KEY `abSeq_UNIQUE` (`abSeq`)
) ENGINE=InnoDB AUTO_INCREMENT=189 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;


CREATE TABLE `authorities` (
                               `authoritiesSeq` bigint NOT NULL AUTO_INCREMENT,
                               `authorityName` varchar(45) COLLATE utf8mb4_german2_ci NOT NULL,
                               `updtTimestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                               `userEmail` varchar(45) COLLATE utf8mb4_german2_ci NOT NULL,
                               PRIMARY KEY (`authoritiesSeq`),
                               UNIQUE KEY `userEmail_UNIQUE` (`userEmail`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;


CREATE TABLE `authorizedToken` (
                                   `tokenSeq` int NOT NULL AUTO_INCREMENT,
                                   `accessToken` varchar(100) COLLATE utf8mb4_german2_ci DEFAULT NULL,
                                   `refreshToken` varchar(100) COLLATE utf8mb4_german2_ci DEFAULT NULL,
                                   `userSeq` bigint NOT NULL,
                                   PRIMARY KEY (`tokenSeq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;


CREATE TABLE `User` (
                        `userSeq` bigint NOT NULL AUTO_INCREMENT,
                        `userEmail` varchar(45) COLLATE utf8mb4_german2_ci DEFAULT NULL,
                        `userPw` varchar(45) COLLATE utf8mb4_german2_ci DEFAULT NULL,
                        `regTimestamp` timestamp NULL DEFAULT NULL,
                        `updtTimestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        `userName` varchar(10) COLLATE utf8mb4_german2_ci DEFAULT NULL,
                        `authorityName` varchar(45) COLLATE utf8mb4_german2_ci DEFAULT NULL,
                        `activeNy` tinyint DEFAULT NULL,
                        `delNy` tinyint DEFAULT '0',
                        PRIMARY KEY (`userSeq`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;

