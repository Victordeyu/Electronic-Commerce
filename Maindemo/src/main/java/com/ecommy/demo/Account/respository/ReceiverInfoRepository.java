package com.ecommy.demo.Account.respository;

import com.ecommy.demo.Common.DataObject.Receiver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReceiverInfoRepository extends JpaRepository<Receiver, String> {
    List<Receiver> findAllByOpenid(String openid);

//    void deleteByRecId(String recId);

//    void deleteByRecIdAndOpenid(String recId, String openid);
}