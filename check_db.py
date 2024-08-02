import os
import sqlite3

# Имя файла базы данных
db_filename = 'MovieApp.db'
db_path = os.path.join(os.getcwd(), db_filename)  # Путь к базе данных в текущей директории

# Проверка наличия файла базы данных
if os.path.exists(db_path):
    print("Файл базы данных найден.")
    try:
        conn = sqlite3.connect(db_path)
        cursor = conn.cursor()
        # Получение списка таблиц
        cursor.execute("SELECT name FROM sqlite_master WHERE type='table';")
        tables = cursor.fetchall()
        print("Таблицы в базе данных:", tables)
        
        # Проверка структуры таблиц
        table_structures = {}
        for table in tables:
            table_name = table[0]
            cursor.execute(f"PRAGMA table_info({table_name});")
            table_structures[table_name] = cursor.fetchall()
        
        # Закрытие соединения
        conn.close()
        print("Структура таблиц:", table_structures)
    except sqlite3.Error as e:
        print("Ошибка при работе с базой данных:", str(e))
else:
    print("Файл базы данных не найден.")
