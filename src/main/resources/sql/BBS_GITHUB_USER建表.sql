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

 Date: 05/09/2022 10:14:28
*/


-- ----------------------------
-- Table structure for BBS_GITHUB_USER
-- ----------------------------
DROP TABLE "SMX"."BBS_GITHUB_USER";
CREATE TABLE "SMX"."BBS_GITHUB_USER" (
  "GITHUB_ID" NUMBER NOT NULL,
  "LOGIN" VARCHAR2(255 BYTE),
  "ACCESS_TOKEN" VARCHAR2(255 BYTE)
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
-- Primary Key structure for table BBS_GITHUB_USER
-- ----------------------------
ALTER TABLE "SMX"."BBS_GITHUB_USER" ADD CONSTRAINT "SYS_C0024753" PRIMARY KEY ("GITHUB_ID");
