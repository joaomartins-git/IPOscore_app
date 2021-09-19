

class Utils:

    @staticmethod
    def normalize_values(column):
        min_value = 0
        max_value = 0
        first_value = 1
        for value in column:
            if value == "na":
                continue
            if first_value == 1:
                min_value = float(value)
                max_value = float(value)
                first_value = 0
            if min_value > float(value):
                min_value = float(value)
            if max_value < float(value):
                max_value = float(value)
        result = []
        for value in column:
            if value == "na":
                continue
            if max_value - min_value > 0:
                result.append((float(value) - min_value) / (max_value - min_value))

        return result
