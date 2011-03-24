delimiter $$
CREATE PROCEDURE createUserHasGroupFromGroup()
BEGIN
    DECLARE l_groupid int;
    DECLARE l_no_more_groups int;
    DEClARE group_csr CURSOR FOR SELECT id from nositer.group;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET l_no_more_groups = 1;
    SET l_no_more_groups = 0;
    OPEN group_csr;
    group_loop: while (l_no_more_groups=0) DO
            fetch group_csr into l_groupid;                      
            if l_no_more_groups = 1 then
                leave group_loop;
            end if;
            insert into nositer.user_has_group(userid, groupid) values(1, l_groupid);  
    END while group_loop;
    CLOSE group_csr;
END$$
delimiter ;