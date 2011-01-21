-- To be used with MySQL Workbench
--
-- by Henry Chan
-- Jan/2011
-- To get to the GRT Shell... View->Advanced->GRT Shell
--
-- in GRT Shell,
-- use shift-enter to paste the code below
-- and ctrl-enter to run
-- adds createdtime, modifiedtime columns to all tables

function foundCreatedtime(i, column)
	if column.name == "createdtime" then
		return true
	else
		return nil
	end
end

function foundModifiedtime(i, column)
	if column.name == "modifiedtime" then
		return true
	else
		return nil
	end
end

function getVarchar()
	dataTypes = grtV.toLua(grtV.getGlobal("/wb/doc/physicalModels/0/rdbms/simpleDatatypes"))
	for i = 1, table.getn(dataTypes) do
		if dataTypes[i].name == "VARCHAR" then
			return dataTypes[i]
		end
	end
end

function getDatetime()
	dataTypes = grtV.toLua(grtV.getGlobal("/wb/doc/physicalModels/0/rdbms/simpleDatatypes"))
	for i = 1, table.getn(dataTypes) do
		if dataTypes[i].name == "DATETIME" then
			return dataTypes[i]
		end
	end
end

function getTimestamp()
	dataTypes = grtV.toLua(grtV.getGlobal("/wb/doc/physicalModels/0/rdbms/simpleDatatypes"))
	for i = 1, table.getn(dataTypes) do
		if dataTypes[i].name == "TIMESTAMP" then
			return dataTypes[i]
		end
	end
end

function getNewColumn(ownerTable, simpleDatatype, name)
	col = grtV.newObj("db.mysql.Column")
    col.owner = ownerTable
    col.name= name
    col.oldName = name
	if simpleDatatype.name == "VARCHAR" then
		col.length = 45
	end
    col.defaultValueIsNull =1
    col.defaultValue ="NULL"
    col.simpleType = simpleDatatype
	return col
end

for i = 1, grtV.getn(grtV.getGlobal"/wb/doc/physicalModels/0/catalog/defaultSchema/tables") do
    tbl = grtV.getGlobal"/wb/doc/physicalModels/0/catalog/defaultSchema/tables"[i]
    cols = grtV.toLua(tbl.columns)  
    if table.foreach(cols, foundCreatedtime) ~= true then
        print ("Adding createdtime to " .. tbl.name .. "\n")
		tbl.columns[grtV.getn(tbl.columns)+1] = getNewColumn(tbl, getTimestamp(), "createdtime")
    end
    if table.foreach(cols, foundModifiedtime) ~= true then
        print ("Adding modifiedtime to " .. tbl.name .. "\n")
		tbl.columns[grtV.getn(tbl.columns)+1] = getNewColumn(tbl, getTimestamp(), "modifiedtime")
    end	
end