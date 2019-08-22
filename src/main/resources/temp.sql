 SELECT * FROM (
            SELECT devId, name, typeName, addr, dataInfo
                FROM
                 (
                  SELECT sd.ID devId, sd.name, sd.addr, sdt.name typeName
                  FROM
                    (
                    SELECT *  FROM share_dev sd
                      WHERE type_id IN (
                                SELECT id  FROM (
                                        WITH RECURSIVE A AS (
                                        SELECT A.*  FROM share_dev_type A
                                            WHERE p_id = 34
                                            AND del_flag = 0 UNION ALL
                                        SELECT b.*  FROM share_dev_type b, A
                                            WHERE A.ID = b.p_id  AND b.del_flag = 0
                                        ) SELECT *  FROM A
                                    ) rs  WHERE rs.limb_leaf = 2
                            )

                        ) sd LEFT JOIN share_dev_type sdt ON sd.type_id = sdt.ID
                        AND sd.del_flag = 0
                    ) dv LEFT JOIN ( SELECT dev_id, data_info dataInfo FROM gis_dev_ext WHERE delete_flag = FALSE ) b
            ON dv.devId = b.dev_id
        ) as jj