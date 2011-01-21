-- To be used with MySQL Workbench
--
-- by Henry Chan
-- May/2009
-- To get to the GRT Shell... View->Advanced->GRT Shell
-- 
-- in GRT Shell,
-- use shift-enter to paste the code below
-- and ctrl-enter to run







for i = 1, grtV.getn(grtV.getGlobal"/wb/doc/physicalModels/0/catalog/defaultSchema/tables") do
    tbl = grtV.getGlobal"/wb/doc/physicalModels/0/catalog/defaultSchema/tables"[i]
   for j = 1, grtV.getn(tbl.columns) do
       col = tbl.columns[j]
       if col.name == "lastmodifiedtime" then
              col.name = "modifiedtime"
       end
   end
end








