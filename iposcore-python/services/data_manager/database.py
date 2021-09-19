import psycopg2
from data.global_variables import GlobalVariables


class DatabaseConnection:
    def __init__(self):
        try:
            self.connection = psycopg2.connect(
                "dbname=\""+GlobalVariables.DatabaseConfigurations.database+"\" " +\
                "user=\""+GlobalVariables.DatabaseConfigurations.user+"\" " +\
                "host=\""+GlobalVariables.DatabaseConfigurations.host+"\" " +\
                "password=\""+GlobalVariables.DatabaseConfigurations.password+"\" " +\
                "port=\""+GlobalVariables.DatabaseConfigurations.port+"\" "
            )
            self.cursor = self.connection.cursor()
        except:
            print("Cannot connect to database")

    def execute_query(self, query):
        success = True
        try:
            self.cursor = self.connection.cursor()
            self.cursor.execute(query)
            self.connection.commit()
        except Exception as error:
            print(query)
            print("Error while executing a query to PostgreSQL", error)
            success = False
        finally:
            if self.connection: self.cursor.close()
        return success

    def remove_ipo_table(self):
        success = self.execute_query("DROP TABLE IF EXISTS ipo_data;")
        if success: print("Drop query successful executed")
        return success

    def create_ipo_table(self):
        self.execute_query("CREATE TABLE IF NOT EXISTS ipo_data(dummy INTEGER NOT NULL PRIMARY KEY;")

        alter_table_command = "ALTER TABLE ipo_data "
        for value in GlobalVariables.DatasetColumns.dates:
            alter_table_command += "ADD COLUMN IF NOT EXISTS \""+value+"\" DATE,"
        for value in GlobalVariables.DatasetColumns.text:
            alter_table_command += "ADD COLUMN IF NOT EXISTS \""+value+"\" TEXT,"
        for value in GlobalVariables.DatasetColumns.binary:
            alter_table_command += "ADD COLUMN IF NOT EXISTS \""+value+"\" SMALLINT,"
        for value in GlobalVariables.DatasetColumns.categorical:
            alter_table_command += "ADD COLUMN IF NOT EXISTS \""+value+"\" INTEGER,"
        for value in GlobalVariables.DatasetColumns.numerical_discrete:
            alter_table_command += "ADD COLUMN IF NOT EXISTS \""+value+"\" INTEGER,"
        for value in GlobalVariables.DatasetColumns.numerical_continuous:
            alter_table_command += "ADD COLUMN IF NOT EXISTS \""+value+"\" REAL,"
        alter_table_command += "ADD COLUMN IF NOT EXISTS \"nº IPO\" BIGINT,"
        alter_table_command += "ADD COLUMN IF NOT EXISTS \"classificação ACS complicações específicas\" BIGINT,"
        alter_table_command += "ADD COLUMN IF NOT EXISTS \"ACS_procedimento\" BIGINT,"
        alter_table_command += "ADD COLUMN IF NOT EXISTS \"Intervenções_ICD10\" BIGINT;"
        self.execute_query(alter_table_command)

        self.execute_query("ALTER TABLE ipo_data DROP CONSTRAINT ipo_data_pkey")
        self.execute_query("ALTER TABLE ipo_data ADD PRIMARY KEY (\"nº IPO\", \"data cirurgia\")")

    def insert_new_binary_columns(self, binary_columns):
        add_columns = "ALTER TABLE ipo_data"
        for column in binary_columns:
            add_columns += "ADD COLUMN IF NOT EXISTS \""+column+"\" SMALLINT DEFAULT 0,"
        add_columns[len(add_columns-1)] = ";"
        self.execute_query(add_columns)

    # primary_key and values must always be an array of arrays
    # EXAMPLE : [ [colunm_name, new_value] , ... ]
    def update_value(self, primary_key, values):
        update_columns = "UPDATE ipo_data "
        for value in values:
            update_columns += "SET \""+value[0]+"\" = \""+value[1]+"\","
        update_columns[len(update_columns)-1] = " WHERE "
        for value in primary_key:
            update_columns += "\""+value[0]+"\" = \""+value[1]+"\" AND "
        update_columns = update_columns[:-2]
        update_columns[len(update_columns)-1] = ";"
        self.execute_query(update_columns)
