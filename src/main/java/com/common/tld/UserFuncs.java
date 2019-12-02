package com.common.tld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import payment.common.Constants;
import payment.server.bean.sys.ServiceMenus;
import payment.server.bean.sys.User;
import payment.server.service.sys.ServiceMenuCfgService;
import payment.server.service.sys.UserCfgService;
import payment.utils.StringUtil;

import java.util.Map;

@Component
public class UserFuncs implements Constants {

    public static UserCfgService userCfgService;
    public static ServiceMenuCfgService serviceMenuCfgService;

    @Autowired
    public void setServiceMenuCfgService(ServiceMenuCfgService serviceMenuCfgService) {
        UserFuncs.serviceMenuCfgService = serviceMenuCfgService;
    }

    @Autowired
    public void setUserCfgService(UserCfgService userCfgService) {
        UserFuncs.userCfgService = userCfgService;
    }

    static Map<Integer, String> idNameMap = CatchMap.UserIdNameMap;

    public static String getRealNameById(Integer id) {
        String name = "no";
        if (idNameMap.containsKey(id)) {
            name = idNameMap.get(id);
        } else {
            User user = userCfgService.getById(id);
            if (user != null)
                name = user.getRealName();
            idNameMap.put(id, name);
        }
        if (StringUtil.isNullString(name))
            name = "no";
        return name;
    }

    public static String getRoleName(Integer role) {
        String ret = "Unknown role";
        ret = User.getRoleMap().get(role);
        if (!StringUtil.checkNotNull(ret)) {
            ret = "Unknown role";
        }
        return ret;
    }

    static Map<Integer, String> serviceMenuIdNameMap = CatchMap.serviceMenuIdNameMap;

    public static String getServiceName(String serviceId) {
        String serviceName = "";
        if (StringUtil.isNullString(serviceId)) {
            serviceName = "Not Configured";
            return serviceName;
        }
        String serviceIds[] = serviceId.split(",");

        for (int i = 0; i < serviceIds.length; i++) {
            Integer keyId = Integer.valueOf(serviceIds[i]);
            if (serviceMenuIdNameMap.containsKey(keyId)) {
                serviceName = serviceName + serviceMenuIdNameMap.get(keyId) + ",";
            } else {
                ServiceMenus item = serviceMenuCfgService.getById(keyId);
                if (null != item) {
                    serviceName = serviceName + item.getServiceName() + ",";
                    serviceMenuIdNameMap.put(keyId, item.getServiceName());
                } else {
                    serviceName = "Not Configured";
                }
            }
        }

        return serviceName;
    }

    /**
     * 角色是合作方
     *
     * @param role
     * @return
     */
    public static Boolean isCooperator(Integer role) {
        Boolean ret = false;
        if (role == User.ROLE_COOPERATOR)
            ret = true;
        return ret;
    }

    /**
     * 角色是商务
     *
     * @param role
     * @return
     */
    public static Boolean isBusiness(Integer role) {
        Boolean ret = false;
        if (role == User.ROLE_BUSSESSER)
            ret = true;
        return ret;
    }

    /**
     * 角色是 运营或者管理员
     *
     * @param role
     * @return
     */
    public static Boolean isOffOrAdmin(Integer role) {
        Boolean ret = false;
        if (role == User.ROLE_ADMINISTRATOR || role == User.ROLE_OPERATOR)
            ret = true;
        return ret;
    }


}
