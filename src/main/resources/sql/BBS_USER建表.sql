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

 Date: 05/09/2022 10:22:08
*/


-- ----------------------------
-- Table structure for BBS_USER
-- ----------------------------
DROP TABLE "SMX"."BBS_USER";
CREATE TABLE "SMX"."BBS_USER" (
  "ID" VARCHAR2(255 BYTE) NOT NULL,
  "USERNAME" VARCHAR2(255 BYTE),
  "PASSWORD" VARCHAR2(255 BYTE),
  "EMAIL" VARCHAR2(255 BYTE),
  "ACTIVATIONCODE" VARCHAR2(255 BYTE),
  "STATUS" VARCHAR2(255 BYTE)
)
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Primary Key structure for table BBS_USER
-- ----------------------------
ALTER TABLE "SMX"."BBS_USER" ADD CONSTRAINT "SYS_C0024754" PRIMARY KEY ("ID");
