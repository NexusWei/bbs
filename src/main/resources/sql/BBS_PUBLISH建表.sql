/*
 Navicat Premium Data Transfer

 Source Server         : 公司测试库——三门峡
 Source Server Type    : Oracle
 Source Server Version : 110200
 Source Host           : 192.168.18.78:1521
 Source Schema         : SMX

 Target Server Type    : Oracle
 Target Server Version : 110200
 File Encoding         : 65001

 Date: 05/09/2022 10:17:31
*/


-- ----------------------------
-- Table structure for BBS_PUBLISH
-- ----------------------------
DROP TABLE "SMX"."BBS_PUBLISH";
CREATE TABLE "SMX"."BBS_PUBLISH" (
  "PUBLISH_ID" VARCHAR2(255 BYTE) NOT NULL,
  "TITLE" VARCHAR2(255 BYTE),
  "DESCRIPTION" VARCHAR2(255 BYTE),
  "TAG" VARCHAR2(255 BYTE),
  "CREATE_TIME" VARCHAR2(255 BYTE),
  "UPDATE_TIME" DATE,
  "USER_ID" VARCHAR2(255 BYTE)
)
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Primary Key structure for table BBS_PUBLISH
-- ----------------------------
ALTER TABLE "SMX"."BBS_PUBLISH" ADD CONSTRAINT "SYS_C0024752" PRIMARY KEY ("PUBLISH_ID");

-- ----------------------------
-- Checks structure for table BBS_PUBLISH
-- ----------------------------
ALTER TABLE "SMX"."BBS_PUBLISH" ADD CONSTRAINT "SYS_C0024751" CHECK ("PUBLISH_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
