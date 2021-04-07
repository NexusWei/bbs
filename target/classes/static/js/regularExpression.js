<!-- 正则验证 start-->

    /**
     * 用户名称验证 4到16位（字母，数字，下划线，减号）
     *
     * @param username
     * @returns {boolean}
     */
    function validUserName(username) {
        // /代表正则表达式开始和结束的标记
        // ^[a-zA-Z0-9_-]{4,16}$
        // ^匹配输入字符串的开始位置，除了大小写和数字外，其它字符不能作为开头
        // [a-zA-Z0-9_-]可以匹配a-z,A-Z,0-9,_- 任意字符
        // {4,16} 最短4个字符，最长16个字符
        // $匹配字符串的结束位置，表达式到此结束，16位以后的全部不匹配
        var pattern = /^[a-zA-Z]{1,16}[a-zA-Z0-9_-]{3,16}$/;
        // .test，test() 方法用于检测一个字符串是否匹配某个模式，如果字符串中有匹配的值返回 true ，否则返回 false
        // 传入username，利用trim移除字符串的头尾空格。
        if (pattern.test(username.trim())) {
            return (true);
        } else {
            return (false);
        }
    }

    /**
     * 邮箱验证
     * @param email
     * @returns {boolean}
     */
    function validEmail(email) {
        var pattern = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
        if (pattern.test(email.trim())) {
            return (true);
        } else {
            return (false);
        }
    }

    /**
     * 用户密码验证 最少6位，最多20位字母或数字的组合
     *
     * @param password
     * @returns {boolean}
     */
    function validPassword(password) {
        var pattern = /^[a-zA-Z0-9]{6,20}$/;
        if (pattern.test(password.trim())) {
            return (true);
        } else {
            return (false);
        }
    }

    /**
     * 参数长度验证：用于验证验证码
     *
     * @param obj
     * @param length
     * @returns {boolean}
     */
    function validLength(obj, length) {
        if (obj.trim().length < length) {
            return true;
        }
        return false;
    }

    /**
     * 参数非空验证：用于验证发布页面
     */
    function validNotNull(obj) {
        if (obj.trim().length >= 1) {
            return true;
        }
        return false;
    }

<!-- 正则验证 end-->

